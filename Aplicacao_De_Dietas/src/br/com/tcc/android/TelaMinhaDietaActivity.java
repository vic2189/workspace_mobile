package br.com.tcc.android;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class TelaMinhaDietaActivity extends Activity{

	private Button buttonMaisDietas;
	private ListView listViewRefeicaoManha,listViewRefeicaoTarde,listViewRefeicaoNoite ;
	public Cursor cursor;
	
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
		
 		
		ListView listViewRefeicaoManha = (ListView) findViewById (R.id.listViewRefeicaoManha);
		ListView listViewRefeicaoTarde = (ListView) findViewById (R.id.listViewRefeicaoTarde);
		ListView listViewRefeicaoNoite = (ListView) findViewById (R.id.listViewRefeicaoNoite);

		/*ArrayAdapter <List> adaptador = new <List> ArrayAdapter (this,
				   android.R.layout.simple_list_item_ 1, android.R.id.text1, values);
		
		 ArrayAdapter <String> adaptador2 = new <String> ArrayAdapter (este,
				   android.R.layout.simple_list_item_ 1, android.R.id.text1, values);
		 
		 ArrayAdapter <String> adaptador3 = new <String> ArrayAdapter (este,
				   android.R.layout.simple_list_item_ 1, android.R.id.text1, values);
				   
				   		
				
		listViewRefeicaoManha.setAdapter(adapter);
		listViewRefeicaoTarde.setAdapter(adapter);
		listViewRefeicaoNoite.setAdapter(adapter);
		 */

		
	}
	
}
