package br.com.tcc.android.telas;

import br.com.tcc.android.R;
import br.com.tcc.android.graficos.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * TelaEstatisticasActivity.
 * 
 * @author Victor Jordao
 * @since Out 6, 2012
 */

public class TelaEstatisticasActivity extends Activity {

	private Button buttonGraficoEscolidas,buttonGraficoSeguidas;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estatisticas);
		buttonGraficoEscolidas = (Button) findViewById(R.id.buttonGraficoEscolidas);
		buttonGraficoEscolidas.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				GraficoRefeicaoEscolida line = new GraficoRefeicaoEscolida();
				Intent lineIntent = line.getIntent(TelaEstatisticasActivity.this);
				startActivity(lineIntent);
				
			}
		});
		buttonGraficoSeguidas = (Button) findViewById(R.id.buttonGraficoSeguidas);
		buttonGraficoSeguidas.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				GraficoRefeicoesSeguidas line = new GraficoRefeicoesSeguidas();
				Intent lineIntent = line.getIntent(TelaEstatisticasActivity.this);
				startActivity(lineIntent);
				
			}
		});
	}
}
