package br.com.tcc.android;

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

	private Button buttonLineGraph,buttonVoltarEstatistica;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estatisticas);
		buttonLineGraph = (Button) findViewById(R.id.buttonGraficoCircular);
		buttonLineGraph.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				GraficoPizza line = new GraficoPizza();
				Intent lineIntent = line.getIntent(TelaEstatisticasActivity.this);
				startActivity(lineIntent);
			}
		});
		buttonVoltarEstatistica = (Button) findViewById(R.id.buttonVoltarEstatistica);
		buttonVoltarEstatistica.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(TelaEstatisticasActivity.this,
						TelaMenuActivity.class);
				startActivity(intent);
			}
		});
	}
}
