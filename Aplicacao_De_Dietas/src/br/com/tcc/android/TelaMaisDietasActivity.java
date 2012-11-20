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
import android.os.AsyncTask;
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
import br.com.tcc.android.dao.AcompanhaDietaDAO;
import br.com.tcc.android.dao.MinhaDietaDAO;
import br.com.tcc.android.dao.PerfilDAO;
import br.com.tcc.android.model.MinhaDieta;

public class TelaMaisDietasActivity extends Activity{

	private static final int OBTER_DIETAS = 1;
	private static final int FAZER_DOWNLOAD = 2;
	private static final String TAG_DIETA = "dieta";
	private static final String HOST = "http://192.168.2.102:8080";

	private static final String URL_DIETAS_DISPONIVEIS = HOST + "/ServidorMobile/resource/mobile/dietas/disponiveis";
	private static final String URL_DOWNLOAD_DIETA = HOST + "/ServidorMobile/resource/mobile/download/";
	private static final String URL_ENVIO_PERFIL = HOST + "/ServidorMobile/resource/mobile/perfil/criacao";

	private List<String> listaDietasDisponiveis = new ArrayList<String>();
	private Map<String, String> mapaDietasDisponiveis = new HashMap<String, String>();
	private ProgressDialog progressDialog;

	private PerfilDAO perfilDAO = new PerfilDAO(TelaMaisDietasActivity.this);
	private MinhaDietaDAO minhaDietaDAO = new MinhaDietaDAO(TelaMaisDietasActivity.this);
	private AcompanhaDietaDAO acompanhaDietaDAO = new AcompanhaDietaDAO(TelaMaisDietasActivity.this);

	@Override
	public void onCreate(Bundle savedInstanceState){
		super .onCreate(savedInstanceState);
		setContentView(R.layout.activity_mais_dietas);

		Conector conector = new Conector(OBTER_DIETAS, "Aguarde...obtendo dietas disponiveis");
		conector.execute();

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
		String chaveIdDieta = listaDietasDisponiveis.get((int) info.id);
		String idDietaEscolhida = mapaDietasDisponiveis.get(chaveIdDieta);
		Conector conector = new Conector(FAZER_DOWNLOAD, "Aguarde...download em andamento");
		conector.execute(idDietaEscolhida);

		return true;

	}

	public void gerarResposta(String mensagem, int flag){
		switch (flag) {
		case 1:
			if(mensagem == "Sucesso"){
				ListView listView = (ListView) findViewById(R.id.listaDietas);

				ArrayAdapter<String> adapter = new ArrayAdapter<String>(TelaMaisDietasActivity.this,
						android.R.layout.simple_list_item_1, android.R.id.text1, listaDietasDisponiveis);

				listView.setAdapter(adapter); 
				registerForContextMenu(listView);
				
			}else{
				Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
			}
			break;

		case 2:

			if(mensagem == "Sucesso"){
				Intent intent = new Intent(TelaMaisDietasActivity.this, TelaMinhaDietaActivity.class);
				startActivity(intent);

				Toast.makeText(this, "Download realizado com sucesso!!!", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
			}

			break;
		}

	}

	private class Conector extends AsyncTask<Object, Boolean, String> {

		String idDietaEscolhida;
		int flag;
		String mensagemBarraProgresso;

		public Conector(int flag, String mensagem){
			this.flag = flag;
			this.mensagemBarraProgresso = mensagem;
		}

		@Override
		protected void onPreExecute() {
			showProgressDialog(mensagemBarraProgresso);
		}

		@Override
		protected String doInBackground(Object... params) {
			String resposta = "";
			WebServiceTask webServiceTask = new WebServiceTask();
			switch(flag){
			case 1:

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
					resposta = "Sucesso";
					
				}else{
					resposta = "Nao foi possivel conectar com o servidor";
				}

				break;
			case 2:
				idDietaEscolhida = (String) params[0];
				Perfil perfil = perfilDAO.getPerfil();

				webServiceTask.enviarPerfil(perfil, idDietaEscolhida, URL_ENVIO_PERFIL);
				String dietaEscolhidaXML = webServiceTask.getXMLFromUrl(URL_DOWNLOAD_DIETA + idDietaEscolhida);

				System.out.println("XML RECEBIDO: " + dietaEscolhidaXML);

				minhaDietaDAO.deletarTodosRegistros();
				acompanhaDietaDAO.deletarTodosRegistros();

				resposta = gravarDieta(webServiceTask, dietaEscolhidaXML);

				break;
			}

			return resposta; 
		}

		@Override
		protected void onPostExecute(String resposta) {
			acompanhaDietaDAO.close();
			minhaDietaDAO.close();
			progressDialog.dismiss();
			gerarResposta(resposta, flag);
			super.onPostExecute(resposta);
		}

		private String gravarDieta(WebServiceTask webServiceTask, String dietaEscolhidaXML) {
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

					minhaDietaDAO.adicionar(minhaDieta);
				}
				return "Sucesso";

			}	
			else{
				return  "Nao foi possivel conectar com o servidor";
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
	}

	private void showProgressDialog(String mensagem) {

		progressDialog = new ProgressDialog(TelaMaisDietasActivity.this);
		progressDialog.setMessage(mensagem);
		progressDialog.setProgressDrawable(TelaMaisDietasActivity.this.getWallpaper());
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setCancelable(false);
		progressDialog.show();

	}
}
