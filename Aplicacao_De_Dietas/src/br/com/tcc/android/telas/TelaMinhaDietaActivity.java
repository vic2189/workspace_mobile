package br.com.tcc.android.telas;import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.com.tcc.android.AdapterListView;
import br.com.tcc.android.ItemListView;
import br.com.tcc.android.R;
import br.com.tcc.android.TelaMaisDietasActivity;
;

public class TelaMinhaDietaActivity extends Activity {

	private Button buttonMaisDietas;	
	private ArrayList<ItemListView> itensManha,itensTarde,itensNoite;
	private ListView listViewManha,listViewTarde,listViewNoite;
    private AdapterListView adapterListView;

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

		listViewManha = (ListView) findViewById(R.id.listViewRefeicaoManha);
		//ArrayAdapter<String> adaptadorManha = new ArrayAdapter<String>(this,
				//android.R.layout.simple_list_item_1, refeicoes);
		//listView1.setAdapter(adaptadorManha);
		listViewTarde = (ListView) findViewById(R.id.listViewRefeicaoTarde);
		listViewNoite = (ListView) findViewById(R.id.listViewRefeicaoNoite);

		createListView();
		listViewManha.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {
				if (position == 0) {					
						Intent intent = new Intent(TelaMinhaDietaActivity.this,
								TelaRefeicaoPrincipalManha.class);
						startActivity(intent);				
				}else{
					Intent intent = new Intent(TelaMinhaDietaActivity.this,
							TelaRefeicaoOpcionalManha.class);
					startActivity(intent);	
				}
			}
		});
		listViewTarde.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {
				if (position == 0) {					
						Intent intent = new Intent(TelaMinhaDietaActivity.this,
								TelaRefeicaoPrincipalTarde.class);
						startActivity(intent);				
				}else{
					Intent intent = new Intent(TelaMinhaDietaActivity.this,
							TelaRefeicaoOpcionalTarde.class);
					startActivity(intent);	
				}
			}
		});
		
		listViewNoite.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {
				if (position == 0) {					
						Intent intent = new Intent(TelaMinhaDietaActivity.this,
								TelaRefeicaoPrincipalNoite.class);
						startActivity(intent);				
				}else{
					Intent intent = new Intent(TelaMinhaDietaActivity.this,
							TelaRefeicaoOpcionalNoite.class);
					startActivity(intent);	
				}
			}
		});
		
	}
	
	 private void createListView() {
	        //Criamos nossa lista que preenchera o ListView
	        itensManha = new ArrayList<ItemListView>();
	        ItemListView itemManhaPrincipal = new ItemListView("REFEI��O PRINCIPAL", R.drawable.cafe);
	        ItemListView itemManhaOpcional = new ItemListView("REFEI��O OPCIONAL", R.drawable.cafe);
	        itensManha.add(itemManhaPrincipal);
	        itensManha.add(itemManhaOpcional);
	        //Cria o adapter
	        adapterListView = new AdapterListView(this, itensManha);
	        //Define o Adapter
	        listViewManha.setAdapter(adapterListView);
	        //Cor quando a lista � selecionada para ralagem.
	        listViewManha.setCacheColorHint(Color.TRANSPARENT);
	        
	        itensTarde = new ArrayList<ItemListView>();
	        ItemListView itemTardePrincipal = new ItemListView("REFEI��O PRINCIPAL", R.drawable.almoco);
	        ItemListView itemTardeOpcional = new ItemListView("REFEI��O OPCIONAL", R.drawable.almoco);
	        itensTarde.add(itemTardePrincipal);
	        itensTarde.add(itemTardeOpcional);
	        adapterListView = new AdapterListView(this, itensTarde);
	        listViewTarde.setAdapter(adapterListView);
	        listViewTarde.setCacheColorHint(Color.TRANSPARENT);
	        
	        itensNoite = new ArrayList<ItemListView>();
	        ItemListView itemNoitePrincipal = new ItemListView("REFEI��O PRINCIPAL", R.drawable.jantar);
	        ItemListView itemNoiteOpcional = new ItemListView("REFEI��O OPCIONAL", R.drawable.jantar);
	        itensNoite.add(itemNoitePrincipal);
	        itensNoite.add(itemNoiteOpcional);
	        adapterListView = new AdapterListView(this, itensNoite);
	        listViewNoite.setAdapter(adapterListView);
	        listViewNoite.setCacheColorHint(Color.TRANSPARENT);
	        
	        
	    }
}
