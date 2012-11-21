package br.com.tcc.android.telas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.tcc.android.R;
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

public class TelaRefeicaoPrincipalNoite extends Activity {

	public ListView list;
	public ArrayList<String> refeicao;
	private String horarioInicial, horarioFinal, categoria;
	private Button buttonEscolheRefPrincipalNoite;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refeicao_principal_noite);
		horarioInicial = "180000";
		horarioFinal = "235959";
		categoria = "PRINCIPAL";
		MinhaDietaDAO minhaDietaDao = new MinhaDietaDAO(this);
		refeicao = minhaDietaDao.getRefeicao(categoria, horarioInicial,
				horarioFinal);
		if (refeicao.isEmpty()) {
			AlertDialog.Builder dialogo = new AlertDialog.Builder(
					TelaRefeicaoPrincipalNoite.this);
			dialogo.setTitle("AVISO");
			dialogo.setMessage("Você não escolheu nenhuma dieta para seguir!"
					+ "Clique em VOLTAR, e acesse Mais Dietas para selecionar uma dieta!");
			dialogo.setPositiveButton("VOLTAR",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							Intent intent = new Intent(
									TelaRefeicaoPrincipalNoite.this,
									TelaMinhaDietaActivity.class);
							startActivity(intent);
							finish();
						}
					});
			dialogo.show();
		} else {
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			String dataInicio = minhaDietaDao.getDataInicio();
			int diasPassados;
			Date data = new Date();
			try {
				data = sd.parse(dataInicio);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date dataAtual = new Date(); // data atual
			Calendar calInicio = Calendar.getInstance();
			calInicio.setTime(data);
			Calendar calAtual = Calendar.getInstance();
			calAtual.setTime(dataAtual);
			diasPassados = diferencaEmDias(calInicio, calAtual);
			if (diasPassados > minhaDietaDao.getPeriodoDieta()){
				AlertDialog.Builder dialogo = new AlertDialog.Builder(
						TelaRefeicaoPrincipalNoite.this);
				dialogo.setTitle("AVISO");
				dialogo.setMessage("O período para realizar sua dieta acabou!"
						+ "Clique em VOLTAR, e acesse Mais Dietas para selecionar ela ou outra dieta para recomeçar!");
				dialogo.setPositiveButton("VOLTAR",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent intent = new Intent(
										TelaRefeicaoPrincipalNoite.this,
										TelaMinhaDietaActivity.class);
								startActivity(intent);
								finish();
							}
						});
				dialogo.show();

			} else {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, refeicao);
			list = (ListView) findViewById(R.id.listViewAlimentosPrincipalNoite);
			list.setAdapter(adapter);

			criaBotoes();
		}
	}
	}

	public void criaBotoes() {

		buttonEscolheRefPrincipalNoite = (Button) findViewById(R.id.buttonEscolheRefPrincipalNoite);
		buttonEscolheRefPrincipalNoite
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						AcompanhaDieta acompanhaDieta = new AcompanhaDieta();
						AcompanhaDietaDAO acompanhaDietaDao = new AcompanhaDietaDAO(
								TelaRefeicaoPrincipalNoite.this);
						MinhaDietaDAO minhaDietaDAO = new MinhaDietaDAO(
								TelaRefeicaoPrincipalNoite.this);
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
								TelaRefeicaoPrincipalNoite.this,
								TelaMinhaDietaActivity.class);
						startActivity(intent);
						finish();
					}
				});

	}

	public static int diferencaEmDias(Calendar c1, Calendar c2) {
		long m1 = c1.getTimeInMillis();
		long m2 = c2.getTimeInMillis();
		return (int) ((m2 - m1) / (24 * 60 * 60 * 1000));
	}

}