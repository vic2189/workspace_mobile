package br.com.tcc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
		System.out.println(dao.checkDataBase());
		
		dao.close();
		criarBotoes();

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}


