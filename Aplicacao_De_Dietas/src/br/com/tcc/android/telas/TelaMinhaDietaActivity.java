package br.com.tcc.android.telas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.tcc.android.AdapterListView;
import br.com.tcc.android.ItemListView;
import br.com.tcc.android.R;
import br.com.tcc.android.dao.AcompanhaDietaDAO;
import br.com.tcc.android.dao.MinhaDietaDAO;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class TelaMinhaDietaActivity extends Activity {

	private Button buttonMaisDietas;
	private ArrayList<ItemListView> itensManha, itensTarde, itensNoite;
	private ListView listViewManha, listViewTarde, listViewNoite;
	private AdapterListView adapterListView;
	TextView textViewDuracaoDieta, textViewDiasDecoridos, textViewSelecaoManha,
			textViewSelecaoTarde, textViewSelecaoNoite,textDietaAtual;
	public String [] horarioInicial = {"000000","120000","180000"};
	public String [] horarioFinal = {"120000","180000","235959"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_minhadieta);

		buttonMaisDietas = (Button) findViewById(R.id.buttonMaisDietas);
		buttonMaisDietas.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Cria a chamada para a pagina
				Intent intent = new Intent(TelaMinhaDietaActivity.this,
						TelaMaisDietasActivity.class);
				startActivity(intent);
			}
		});
		
		textViewDuracaoDieta = (TextView) findViewById(R.id.textViewDuracaoDieta);
		textViewDiasDecoridos = (TextView) findViewById(R.id.textViewDiasDecoridos);
		textViewSelecaoManha = (TextView) findViewById(R.id.textViewSelecaoManha);
		textViewSelecaoTarde = (TextView) findViewById(R.id.textViewSelecaoTarde);
		textViewSelecaoNoite = (TextView) findViewById(R.id.textViewSelecaoNoite);
		textDietaAtual = (TextView) findViewById(R.id.textDietaAtual);
		
		MinhaDietaDAO minhaDietaDao =  new MinhaDietaDAO(this);
		textDietaAtual.setText("DIETA ATUAL: " + minhaDietaDao.getNomeDieta());
		if(minhaDietaDao.getPeriodoDieta() != 0){
		textViewDuracaoDieta.setText("DURAÇÃO DIETA: " + minhaDietaDao.getPeriodoDieta());
		}
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		String dataInicio = minhaDietaDao.getDataInicio();
		int diasPassados;
		Date data = new Date();
		try {
			data = sd.parse(dataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date dataAtual = new Date(); // data atual
		Calendar calInicio = Calendar.getInstance();
		calInicio.setTime(data);
		Calendar calAtual = Calendar.getInstance();
		calAtual.setTime(dataAtual);
		diasPassados = diferencaEmDias(calInicio, calAtual);		
		textViewDiasDecoridos.setText("DIAS DECORRIDOS: " + diasPassados);
		AcompanhaDietaDAO acompanhaDao = new AcompanhaDietaDAO(this);
		textViewSelecaoManha.setText("REFEIÇÃO SELECIONADA MANHA: " + acompanhaDao.getRefeicaoEscolida(diasPassados, horarioInicial[0], horarioFinal[0]));
		textViewSelecaoTarde.setText("REFEIÇÃO SELECIONADA TARDE: " + acompanhaDao.getRefeicaoEscolida(diasPassados, horarioInicial[1], horarioFinal[1]));
		textViewSelecaoNoite.setText("REFEIÇÃO SELECIONADA NOITE: " + acompanhaDao.getRefeicaoEscolida(diasPassados, horarioInicial[2], horarioFinal[2]));
		
		
		
		
		listViewManha = (ListView) findViewById(R.id.listViewRefeicaoManha);
		listViewTarde = (ListView) findViewById(R.id.listViewRefeicaoTarde);
		listViewNoite = (ListView) findViewById(R.id.listViewRefeicaoNoite);

		createListView();
		listViewManha.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					Intent intent = new Intent(TelaMinhaDietaActivity.this,
							TelaRefeicaoPrincipalManha.class);
					startActivity(intent);
					finish();
				} else {
					Intent intent = new Intent(TelaMinhaDietaActivity.this,
							TelaRefeicaoOpcionalManha.class);
					startActivity(intent);
					finish();
				}
			}
		});
		listViewTarde.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					Intent intent = new Intent(TelaMinhaDietaActivity.this,
							TelaRefeicaoPrincipalTarde.class);
					startActivity(intent);
					finish();
				} else {
					Intent intent = new Intent(TelaMinhaDietaActivity.this,
							TelaRefeicaoOpcionalTarde.class);
					startActivity(intent);
					finish();
				}
			}
		});

		listViewNoite.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					Intent intent = new Intent(TelaMinhaDietaActivity.this,
							TelaRefeicaoPrincipalNoite.class);
					startActivity(intent);
					finish();
				} else {
					Intent intent = new Intent(TelaMinhaDietaActivity.this,
							TelaRefeicaoOpcionalNoite.class);
					startActivity(intent);
					finish();
				}
			}
		});

	}

	private void createListView() {
		// Criamos nossa lista que preenchera o ListView
		itensManha = new ArrayList<ItemListView>();
		ItemListView itemManhaPrincipal = new ItemListView(
				"REFEIÇÃO PRINCIPAL", R.drawable.cafe);
		ItemListView itemManhaOpcional = new ItemListView("REFEIÇÃO OPCIONAL",
				R.drawable.cafe);
		itensManha.add(itemManhaPrincipal);
		itensManha.add(itemManhaOpcional);
		// Cria o adapter
		adapterListView = new AdapterListView(this, itensManha);
		// Define o Adapter
		listViewManha.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para ralagem.
		listViewManha.setCacheColorHint(Color.TRANSPARENT);

		itensTarde = new ArrayList<ItemListView>();
		ItemListView itemTardePrincipal = new ItemListView(
				"REFEIÇÃO PRINCIPAL", R.drawable.almoco);
		ItemListView itemTardeOpcional = new ItemListView("REFEIÇÃO OPCIONAL",
				R.drawable.almoco);
		itensTarde.add(itemTardePrincipal);
		itensTarde.add(itemTardeOpcional);
		adapterListView = new AdapterListView(this, itensTarde);
		listViewTarde.setAdapter(adapterListView);
		listViewTarde.setCacheColorHint(Color.TRANSPARENT);

		itensNoite = new ArrayList<ItemListView>();
		ItemListView itemNoitePrincipal = new ItemListView(
				"REFEIÇÃO PRINCIPAL", R.drawable.jantar);
		ItemListView itemNoiteOpcional = new ItemListView("REFEIÇÃO OPCIONAL",
				R.drawable.jantar);
		itensNoite.add(itemNoitePrincipal);
		itensNoite.add(itemNoiteOpcional);
		adapterListView = new AdapterListView(this, itensNoite);
		listViewNoite.setAdapter(adapterListView);
		listViewNoite.setCacheColorHint(Color.TRANSPARENT);

	}
	public static int diferencaEmDias(Calendar c1, Calendar c2) {
		long m1 = c1.getTimeInMillis();
		long m2 = c2.getTimeInMillis();
		return (int) ((m2 - m1) / (24 * 60 * 60 * 1000));

	}
	}
