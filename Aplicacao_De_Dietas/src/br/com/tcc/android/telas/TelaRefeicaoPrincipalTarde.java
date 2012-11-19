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

public class TelaRefeicaoPrincipalTarde extends Activity {

	public ListView list;
	public ArrayList<String> refeicao;
	private String horarioInicial, horarioFinal,categoria;
	private Button buttonEscolheRefPrincipalTarde,buttonVoltarRefPrincipalTarde;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refeicao_principal_tarde);
		horarioInicial = "120000";
		horarioFinal = "180000";
		categoria = "PRINCIPAL";
		MinhaDietaDAO minhaDietaDao = new MinhaDietaDAO(this);
		refeicao = minhaDietaDao.getRefeicao(categoria, horarioInicial, horarioFinal) ;
		if(refeicao.isEmpty()){
			AlertDialog.Builder dialogo = new
			AlertDialog.Builder(TelaRefeicaoPrincipalTarde.this);
			dialogo.setTitle("AVISO");
			dialogo.setMessage("Você não escolheu nenhuma dieta para seguir!" +
			"Clique em VOLTAR, e acesse Mais Dietas para selecionar uma dieta!");
			dialogo.setPositiveButton("VOLTAR", new DialogInterface.OnClickListener() {               
	            public void onClick(DialogInterface dialog, int id) {                     
	            	Intent intent = new Intent(TelaRefeicaoPrincipalTarde.this,
							TelaMinhaDietaActivity.class);
					startActivity(intent); }});
			dialogo.show();
		}else{
		   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1,refeicao );
	        list = (ListView) findViewById(R.id.listViewAlimentosPrincipalTarde);
	        list.setAdapter(adapter);

	        criaBotoes();
	}
	}
	public void criaBotoes() {
		
		buttonEscolheRefPrincipalTarde = (Button) findViewById(R.id.buttonEscolheRefPrincipalTarde);
		
		buttonVoltarRefPrincipalTarde = (Button) findViewById(R.id.buttonVoltarRefPrincipalTarde);
		buttonVoltarRefPrincipalTarde.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(TelaRefeicaoPrincipalTarde.this,
						TelaMinhaDietaActivity.class);
				startActivity(intent);

			}
		});
	}
}