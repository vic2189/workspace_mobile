package br.com.tcc.android;

import br.com.tcc.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaMinhaDietaActivity extends Activity{

	private Button buttonMaisDietas;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super .onCreate(savedInstanceState);
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
		
	}
	
}
