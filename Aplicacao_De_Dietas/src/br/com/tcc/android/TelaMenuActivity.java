package br.com.tcc.android;

import br.com.tcc.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class TelaMenuActivity extends Activity implements
		android.view.View.OnClickListener {

	private Button buttonPerfil;
	private Button buttonMaisDietas;
	private Button buttonMinhaDieta;
	private Button buttonEstatisticas;
	private android.view.View.OnClickListener telaChamada;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);// Mapeando a pagina
												// activity_menu
		PerfilDAO dao = new PerfilDAO(this);
		System.out.println(dao.checkDataBase());
		
		dao.close();
		criaBotao();

	}

	public void criaBotao() {
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
		buttonMaisDietas = (Button) findViewById(R.id.buttonMaisDietas);
		buttonMaisDietas.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Cria a chamada para a pagina
				Intent intent = new Intent(TelaMenuActivity.this,
						TelaMaisDietasActivity.class);
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


