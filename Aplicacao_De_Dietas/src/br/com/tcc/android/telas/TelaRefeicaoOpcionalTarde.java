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

public class TelaRefeicaoOpcionalTarde extends Activity {

	public ListView list;
	public ArrayList<String> refeicao;
	private String horarioInicial, horarioFinal,categoria;
	private Button buttonEscolheRefOpcionalTarde,buttonVoltarRefOpcionalTarde;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refeicao_opcional_tarde);
		horarioInicial = "120000";
		horarioFinal = "180000";
		categoria = "OPCIONAL";
		MinhaDietaDAO minhaDietaDao = new MinhaDietaDAO(this);
		refeicao = minhaDietaDao.getRefeicao(categoria, horarioInicial, horarioFinal) ;
		if(refeicao.isEmpty()){
			AlertDialog.Builder dialogo = new
			AlertDialog.Builder(TelaRefeicaoOpcionalTarde.this);
			dialogo.setTitle("AVISO");
			dialogo.setMessage("Não a Refeição Opcional cadastrada, clique no botão VOLTAR e escolha a opção Refeição Principal.");
			dialogo.setPositiveButton("VOLTAR", new DialogInterface.OnClickListener() {               
	            public void onClick(DialogInterface dialog, int id) {                     
	            	Intent intent = new Intent(TelaRefeicaoOpcionalTarde.this,
							TelaMinhaDietaActivity.class);
					startActivity(intent); }});
			dialogo.show();
		}else{
		   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1,refeicao );
	        list = (ListView) findViewById(R.id.listViewAlimentosOpcionalTarde);
	        list.setAdapter(adapter);

	        criaBotoes();
	}
	}
	public void criaBotoes() {
		
		buttonEscolheRefOpcionalTarde = (Button) findViewById(R.id.buttonEscolheRefOpcionalTarde);
		
		buttonVoltarRefOpcionalTarde = (Button) findViewById(R.id.buttonVoltarRefOpcionalTarde);
		buttonVoltarRefOpcionalTarde.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(TelaRefeicaoOpcionalTarde.this,
						TelaMinhaDietaActivity.class);
				startActivity(intent);

			}
		});
	}
}