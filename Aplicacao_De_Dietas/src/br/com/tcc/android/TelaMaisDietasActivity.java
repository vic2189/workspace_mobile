package br.com.tcc.android;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.tcc.android.comunicacao.WebServiceTask;

public class TelaMaisDietasActivity extends Activity{

	private static final String TAG_DIETA = "dieta";
	private static final String URL = "http://192.168.2.102:8080/ServidorMobile/resource/mobile/dietas/disponiveis";
	ArrayAdapter<String> adaptador = null;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super .onCreate(savedInstanceState);
		setContentView(R.layout.activity_mais_dietas);
		
		List<String> listaDietasDisponiveis = new ArrayList<String>();
		
		WebServiceTask webServiceTask = new WebServiceTask();

		String dietaXml = webServiceTask.getXMLFromUrl(URL);
		
		if(dietaXml != null){
			Document document = webServiceTask.getDomElement(dietaXml);

			NodeList tagDieta = document.getElementsByTagName(TAG_DIETA);

			for (int i = 0; i < tagDieta.getLength(); i++) {
				Element e = (Element) tagDieta.item(i);
				String id = webServiceTask.getValue(e, "id");
				String nomeDieta = webServiceTask.getValue(e, "nome");

				System.out.println("ID :" + id);
				System.out.println("NOME: " + nomeDieta);
				System.out.println("=====================");

				listaDietasDisponiveis.add(nomeDieta);
			}
		}else{
			Toast.makeText(getApplicationContext(), "Nao foi possivel conectar com o servidor", Toast.LENGTH_SHORT).show();
		}
		
		ListView listView = (ListView) findViewById(R.id.listaDietas);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, listaDietasDisponiveis);

		listView.setAdapter(adapter); 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id){
				Toast.makeText(getApplicationContext(), ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
			}
		});

	}
}
