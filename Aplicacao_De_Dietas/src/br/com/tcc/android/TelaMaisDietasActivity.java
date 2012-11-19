package br.com.tcc.android;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.tcc.android.comunicacao.WebServiceTask;
import br.com.tcc.android.dao.MinhaDietaDAO;
import br.com.tcc.android.dao.PerfilDAO;
import br.com.tcc.android.model.MinhaDieta;

public class TelaMaisDietasActivity extends Activity{

	private static final String TAG_DIETA = "dieta";
	private static final String HOST = "http://192.168.2.102:8080";

	private static final String URL_DIETAS_DISPONIVEIS = HOST + "/ServidorMobile/resource/mobile/dietas/disponiveis";
	private static final String URL_DOWNLOAD_DIETA = HOST + "/ServidorMobile/resource/mobile/download/";
	private static final String URL_ENVIO_PERFIL = HOST + "/ServidorMobile/resource/mobile/perfil/criacao";

	List<String> listaDietasDisponiveis = new ArrayList<String>();
	Map<String, String> mapaDietasDisponiveis = new HashMap<String, String>();
	ArrayAdapter<String> adaptador = null;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super .onCreate(savedInstanceState);
		setContentView(R.layout.activity_mais_dietas);

		final ProgressDialog progress = ProgressDialog.show(TelaMaisDietasActivity.this, "Aguarde", "Obtendo dietas disponiveis!", true);


		WebServiceTask webServiceTask = new WebServiceTask();

		String dietaXml = webServiceTask.getXMLFromUrl(URL_DIETAS_DISPONIVEIS);

		if(dietaXml != null){
			Document document = webServiceTask.getDomElement(dietaXml);

			NodeList tagDieta = document.getElementsByTagName(TAG_DIETA);

			for (int i = 0; i < tagDieta.getLength(); i++) {
				Element e = (Element) tagDieta.item(i);
				String id = webServiceTask.getValue(e, "identificacaoDieta");
				String nomeDieta = webServiceTask.getValue(e, "nome");

				System.out.println("ID :" + id);
				System.out.println("NOME: " + nomeDieta);
				System.out.println("=====================");

				mapaDietasDisponiveis.put(nomeDieta, id);

				listaDietasDisponiveis.add(nomeDieta);
			}
		}else{
			Toast.makeText(getApplicationContext(), "Nao foi possivel conectar com o servidor", Toast.LENGTH_SHORT).show();
		}

		ListView listView = (ListView) findViewById(R.id.listaDietas);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, listaDietasDisponiveis);

		listView.setAdapter(adapter); 


		registerForContextMenu(listView);
		progress.dismiss();

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_download, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {

		case R.id.opcao_download:
			final ProgressDialog progress = ProgressDialog.show(TelaMaisDietasActivity.this, "Aguarde", "Fazendo download da dieta!");
			String chaveIdDieta = listaDietasDisponiveis.get((int) info.id);
			String idDietaEscolhida = mapaDietasDisponiveis.get(chaveIdDieta);

			WebServiceTask webServiceTask = new WebServiceTask();
			PerfilDAO perfilDAO = new PerfilDAO(TelaMaisDietasActivity.this);
			Perfil perfil = perfilDAO.getPerfil();
			webServiceTask.enviarPerfil(perfil, idDietaEscolhida, URL_ENVIO_PERFIL);

			String dietaEscolhidaXML = webServiceTask.getXMLFromUrl(URL_DOWNLOAD_DIETA + idDietaEscolhida);

			System.out.println("XML RECEBIDO: " + dietaEscolhidaXML);

			gravarDieta(dietaEscolhidaXML, webServiceTask);
			progress.dismiss();

			Intent intent = new Intent(TelaMaisDietasActivity.this,
					TelaMinhaDietaActivity.class);
			startActivity(intent);

			Toast.makeText(this, "Download realizado com sucesso!!!", Toast.LENGTH_LONG).show();
			return true;

		default:
			return super.onContextItemSelected(item);
		}
	}

	private void gravarDieta(String dietaEscolhidaXML, WebServiceTask webServiceTask) {
		MinhaDietaDAO minhaDietaDAO = new MinhaDietaDAO(TelaMaisDietasActivity.this);
		minhaDietaDAO.deletarTodosRegistros();

		if(dietaEscolhidaXML != null){
			Document document = webServiceTask.getDomElement(dietaEscolhidaXML);

			NodeList tagDieta = document.getElementsByTagName(TAG_DIETA);

			Element e = (Element) tagDieta.item(0);
			Integer identificacaoDieta = Integer.parseInt(webServiceTask.getValue(e, "identificacaoDieta"));
			String nomeDieta = webServiceTask.getValue(e, "nome");
			String duracaoDieta = webServiceTask.getValue(e, "duracao");

			NodeList tagRefeicao = document.getElementsByTagName("refeicao");

			String[] listaMeusAlimentos = new String[5];
			String[] listaQuantidadeAlimento = new String[5];

			for (int i = 0; i < tagRefeicao.getLength(); i++) {
				Integer id = i+1;
				Element elementoRefeicao = (Element) tagRefeicao.item(i);
				NodeList tagAlimento = elementoRefeicao.getElementsByTagName("alimento");
				for (int j = 0; j < tagAlimento.getLength(); j++) {
					Element elementoAlimento = (Element) tagAlimento.item(j);
					String nomeAlimento = webServiceTask.getValue(elementoAlimento, "nome");
					String quantidadeAlimento = webServiceTask.getValue(elementoAlimento, "quantidade");
					String medida = webServiceTask.getValue(elementoAlimento, "medida");
					listaMeusAlimentos[j] = nomeAlimento;
					listaQuantidadeAlimento[j] = quantidadeAlimento + " " + medida;
				}

				String tipoRefeicao = webServiceTask.getValue(elementoRefeicao, "tipo");
				String horarioRefeicao = webServiceTask.getValue(elementoRefeicao, "horario");


				MinhaDieta minhaDieta = criarMinhaDieta(identificacaoDieta,	nomeDieta, duracaoDieta,
						listaMeusAlimentos, listaQuantidadeAlimento, id, tipoRefeicao, horarioRefeicao);

				logarDieta(nomeDieta, duracaoDieta, listaMeusAlimentos, listaQuantidadeAlimento, tipoRefeicao, horarioRefeicao);
				minhaDietaDAO.adicionar(minhaDieta);
			}

			minhaDietaDAO.close();

		}else{
			Toast.makeText(getApplicationContext(), "Nao foi possivel conectar com o servidor", Toast.LENGTH_SHORT).show();
		}
	}

	private MinhaDieta criarMinhaDieta(Integer identificacaoDieta, String nomeDieta,
			String duracaoDieta, String[] listaMeusAlimentos,
			String[] listaQuantidadeAlimento, Integer id, String tipoRefeicao,
			String horarioRefeicao) {
		
		MinhaDieta minhaDieta = new MinhaDieta();
		String data = getData();
		minhaDieta.setId(id);
		minhaDieta.setIdentificacaoDieta(identificacaoDieta);
		minhaDieta.setNomeDieta(nomeDieta);
		minhaDieta.setDuracaoDieta(duracaoDieta);
		minhaDieta.setHorarioRefeicao(horarioRefeicao);
		minhaDieta.setTipoRefeicao(tipoRefeicao);
		minhaDieta.setAlimentos(listaMeusAlimentos);
		minhaDieta.setQuantidades(listaQuantidadeAlimento);
		minhaDieta.setDataDownload(data);
		return minhaDieta;
	}

	private String getData() {
		Calendar calendar = Calendar.getInstance();
		Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
		Integer mes = calendar.get(Calendar.MONTH) +1;
		Integer ano = calendar.get(Calendar.YEAR);
		String data = dia + "/" + mes + "/" +ano;
		return data;
	}

	private void logarDieta(String nomeDieta, String duracaoDieta, String[] listaMeusAlimentos, 
			String[] listaQuantidadeAlimento, String  tipoRefeicao, String horarioRefeicao) {


		System.out.println("===============================");
		System.out.println("DETALHES DA REFEICAO BAIXADA:");
		System.out.println("NOME DA DIETA: "+ nomeDieta);
		System.out.println("DURACAO DA DIETA: "+ duracaoDieta+ " dias");
		System.out.println("TIPO DA REFEICAO: "+ tipoRefeicao);
		System.out.println("HORARIO DA REFEICAO: "+ horarioRefeicao);

		for (int j = 0; j < listaMeusAlimentos.length; j++) {
			System.out.println("ALIMENTO: " + listaMeusAlimentos[j]);
			System.out.println("QUANTIDADE: " + listaQuantidadeAlimento[j]);
			System.out.println("===============================");
		}
	}
}
