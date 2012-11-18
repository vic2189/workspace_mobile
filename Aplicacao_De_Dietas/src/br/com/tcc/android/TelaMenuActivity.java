package br.com.tcc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TelaMenuActivity extends Activity implements
		android.view.View.OnClickListener {

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
		buttonEstatisticas.setOnClickListener(new View.OnClickListener(){
			public void onClick(View arg0) {
			Toast.makeText(
					getBaseContext(),
					"Não há Perfil Cadastrado,"
							+ " clique em Perfil para cadastrar um Perfil!",
					Toast.LENGTH_LONG).show();
			}}
		);

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


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}


