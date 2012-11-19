package br.com.tcc.android.telas;

import java.util.ArrayList;

import br.com.tcc.android.R;
import br.com.tcc.android.TelaEditarPerfilActivity;
import br.com.tcc.android.TelaMinhaDietaActivity;
import br.com.tcc.android.TelaPerfilActivity;
import br.com.tcc.android.dao.MinhaDietaDAO;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TelaRefeicaoOpcionalManha extends Activity {

	public ListView list;
	public ArrayList<String> refeicao;
	private String horarioInicial, horarioFinal,categoria;
	private Button buttonEscolheRefOpcionalManha,buttonVoltarRefOpcionalManha;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refeicao_opcional_manha);
		horarioInicial = "000000";
		horarioFinal = "120000";
		categoria = "OPCIONAL";
		MinhaDietaDAO minhaDietaDao = new MinhaDietaDAO(this);
		refeicao = minhaDietaDao.getRefeicao(categoria, horarioInicial, horarioFinal) ;
		if(refeicao.isEmpty()){
			AlertDialog.Builder dialogo = new
			AlertDialog.Builder(TelaRefeicaoOpcionalManha.this);
			dialogo.setTitle("AVISO");
			dialogo.setMessage("Não a Refeição Opcional cadastrada, clique no botão VOLTAR e escolha a opção Refeição Principal.");
			dialogo.setPositiveButton("VOLTAR", new DialogInterface.OnClickListener() {               
	            public void onClick(DialogInterface dialog, int id) {                     
	            	Intent intent = new Intent(TelaRefeicaoOpcionalManha.this,
							TelaMinhaDietaActivity.class);
					startActivity(intent); }});
			dialogo.show();
		}else{
		   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1,refeicao );
	        list = (ListView) findViewById(R.id.listViewAlimentosOpcionalManha);
	        list.setAdapter(adapter);
	        
	        criaBotoes();
	}
	}
	public void criaBotoes() {
		
		buttonEscolheRefOpcionalManha = (Button) findViewById(R.id.buttonEscolheRefOpcionalManha);
		
		buttonVoltarRefOpcionalManha = (Button) findViewById(R.id.buttonVoltarRefOpcionalManha);
		buttonVoltarRefOpcionalManha.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(TelaRefeicaoOpcionalManha.this,
						TelaMinhaDietaActivity.class);
				startActivity(intent);

			}
		});
	}
}