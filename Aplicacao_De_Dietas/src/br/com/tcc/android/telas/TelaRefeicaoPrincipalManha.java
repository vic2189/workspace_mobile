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

public class TelaRefeicaoPrincipalManha extends Activity {

	public ListView list;
	public ArrayList<String> refeicao;
	private String horarioInicial, horarioFinal, categoria;
	private Button buttonEscolheRefManhaPrincipal,
			buttonVoltarRefPrincipalManha;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refeicao_principal_manha);
		horarioInicial = "000000";
		horarioFinal = "120000";
		categoria = "PRINCIPAL";
		MinhaDietaDAO minhaDietaDao = new MinhaDietaDAO(this);
		refeicao = minhaDietaDao.getRefeicao(categoria, horarioInicial,
				horarioFinal);
		if (refeicao.isEmpty()) {
			AlertDialog.Builder dialogo = new AlertDialog.Builder(
					TelaRefeicaoPrincipalManha.this);
			dialogo.setTitle("AVISO");
			dialogo.setMessage("Você não escolheu nenhuma dieta para seguir!" +
					"Clique em VOLTAR, e acesse Mais Dietas para selecionar uma dieta!");
			dialogo.setPositiveButton("VOLTAR",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							Intent intent = new Intent(
									TelaRefeicaoPrincipalManha.this,
									TelaMinhaDietaActivity.class);
							startActivity(intent);
						}
					});
			dialogo.show();
		} else {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, refeicao);
			list = (ListView) findViewById(R.id.listViewAlimentosPrincipalManha);
			list.setAdapter(adapter);

			criaBotoes();
		}
	}

	public void criaBotoes() {

		buttonEscolheRefManhaPrincipal = (Button) findViewById(R.id.buttonEscolheRefManhaPrincipal);

		buttonVoltarRefPrincipalManha = (Button) findViewById(R.id.buttonVoltarRefPrincipalManha);
		buttonVoltarRefPrincipalManha
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent(
								TelaRefeicaoPrincipalManha.this,
								TelaMinhaDietaActivity.class);
						startActivity(intent);

					}
				});
	}
}