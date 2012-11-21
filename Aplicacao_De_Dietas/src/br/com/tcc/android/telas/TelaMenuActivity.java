package br.com.tcc.android.telas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import br.com.tcc.android.R;
import br.com.tcc.android.comunicacao.WebServiceTask;
import br.com.tcc.android.dao.AcompanhaDietaDAO;
import br.com.tcc.android.dao.MinhaDietaDAO;
import br.com.tcc.android.dao.PerfilDAO;
import br.com.tcc.android.model.Perfil;

public class TelaMenuActivity extends Activity implements
		android.view.View.OnClickListener {

	private static final String HOST = "10.61.1.228:8080";
	private Button buttonPerfil;
	private Button buttonMinhaDieta;
	private Button buttonEstatisticas;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);// Mapeando a pagina
												// activity_menu

		PerfilDAO dao = new PerfilDAO(this);
		Perfil perfil = dao.getPerfil();
		dao.close();
		if (perfil.getIdPerfil() != null) {
			MinhaDietaDAO minhaDietaDao = new MinhaDietaDAO(this);
			if (minhaDietaDao.getPossuiDieta()) {
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
				if (diasPassados > minhaDietaDao.getPeriodoDieta()) {
					AcompanhaDietaDAO acompanhaDao = new AcompanhaDietaDAO(this);
					WebServiceTask web = new WebServiceTask();
					boolean podeDeletar = web.enviarPerfil(perfil,
							minhaDietaDao.getIdentificacaoDieta(),
							HOST + "/ServidorMobile/resource/mobile/perfil/atualizacao/"
									+ acompanhaDao.getStatusDieta());
					if(podeDeletar){
						acompanhaDao.deletarTodosRegistros();
						minhaDietaDao.deletarTodosRegistros();
					}
				}
			}
				criarBotoes();
			
			} else {
				criaBotoesSemPerfil();
			
		}

	}

	public void criarBotoes() {
		// Listener ira ouvir os eventos do botao
		buttonEstatisticas = (Button) findViewById(R.id.buttonEstatisticas);
		buttonEstatisticas.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Cria a chamada para a pagina
				Intent intent = new Intent(TelaMenuActivity.this,
						TelaEstatisticasActivity.class);
				startActivity(intent);
				
			}
		});

		buttonPerfil = (Button) findViewById(R.id.buttonPerfil);
		buttonPerfil.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Cria a chamada para a pagina
				Intent intent = new Intent(TelaMenuActivity.this,
						TelaPerfilActivity.class);
				startActivity(intent);
				
			}
		});

		buttonMinhaDieta = (Button) findViewById(R.id.buttonMinhaDieta);
		buttonMinhaDieta.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Cria a chamada para a pagina
				Intent intent = new Intent(TelaMenuActivity.this,
						TelaMinhaDietaActivity.class);
				startActivity(intent);
				

			}
		});
	}

	private void criaBotoesSemPerfil() {
		// Listener ira ouvir os eventos do botao
		buttonEstatisticas = (Button) findViewById(R.id.buttonEstatisticas);
		buttonEstatisticas.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Toast.makeText(
						getBaseContext(),
						"Não há Perfil Cadastrado,"
								+ " clique em Perfil para cadastrar um Perfil!",
						Toast.LENGTH_LONG).show();
			}
		});

		buttonPerfil = (Button) findViewById(R.id.buttonPerfil);
		buttonPerfil.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Cria a chamada para a pagina
				Intent intent = new Intent(TelaMenuActivity.this,
						TelaPerfilActivity.class);
				startActivity(intent);
				
			}
		});

		buttonMinhaDieta = (Button) findViewById(R.id.buttonMinhaDieta);
		buttonMinhaDieta.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(
						getBaseContext(),
						"Não há Perfil Cadastrado,"
								+ " clique em Perfil para cadastrar um Perfil!",
						Toast.LENGTH_LONG).show();

			}
		});
	}

	public static int diferencaEmDias(Calendar c1, Calendar c2) {
		long m1 = c1.getTimeInMillis();
		long m2 = c2.getTimeInMillis();
		return (int) ((m2 - m1) / (24 * 60 * 60 * 1000));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
