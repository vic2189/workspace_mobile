package br.com.tcc.android.telas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.tcc.android.R;
import br.com.tcc.android.TelaEditarPerfilActivity;
import br.com.tcc.android.TelaMinhaDietaActivity;
import br.com.tcc.android.TelaPerfilActivity;
import br.com.tcc.android.dao.AcompanhaDietaDAO;
import br.com.tcc.android.dao.MinhaDietaDAO;
import br.com.tcc.android.model.AcompanhaDieta;
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
			dialogo.setMessage("Voc� n�o escolheu nenhuma dieta para seguir!" +
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
		buttonEscolheRefPrincipalTarde.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AcompanhaDieta acompanhaDieta = new AcompanhaDieta();
				AcompanhaDietaDAO acompanhaDietaDao = new AcompanhaDietaDAO(
						TelaRefeicaoPrincipalTarde.this);
				MinhaDietaDAO minhaDietaDAO = new MinhaDietaDAO(
						TelaRefeicaoPrincipalTarde.this);
				acompanhaDieta = minhaDietaDAO.getAcompanhaDieta(
						categoria, horarioInicial, horarioFinal);
				minhaDietaDAO.close();
				SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
				String dataInicio = acompanhaDieta.getDataInicio();
				try {
					Date data = sd.parse(dataInicio);
					Date dataAtual = new Date(); // data atual
					Calendar calInicio = Calendar.getInstance();
					calInicio.setTime(data);
					Calendar calAtual = Calendar.getInstance();
					calAtual.setTime(dataAtual);
					acompanhaDieta.setDiaDieta(diferencaEmDias(
							calInicio, calAtual));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				acompanhaDietaDao.adicionar(acompanhaDieta);
				acompanhaDietaDao.close();
				Intent intent = new Intent(
						TelaRefeicaoPrincipalTarde.this,
						TelaMinhaDietaActivity.class);
				startActivity(intent);

			}
		});
		buttonVoltarRefPrincipalTarde = (Button) findViewById(R.id.buttonVoltarRefPrincipalTarde);
		buttonVoltarRefPrincipalTarde.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(TelaRefeicaoPrincipalTarde.this,
						TelaMinhaDietaActivity.class);
				startActivity(intent);

			}
		});
	}
	
	public static int diferencaEmDias(Calendar c1, Calendar c2) {
		long m1 = c1.getTimeInMillis();
		long m2 = c2.getTimeInMillis();
		return (int) ((m2 - m1) / (24 * 60 * 60 * 1000));
	}
	
}