package br.com.tcc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TelaPerfilActivity extends Activity {

	TextView textMostraNome, textMostraEmail, textMostraIdade,
			textMostraAltura, textMostraPeso, textMostraGenero;
	private Button buttonEditarPerfil;
	private Button buttonVoltar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);
		textMostraNome = (TextView) findViewById(R.id.textMostraNome);
		textMostraEmail = (TextView) findViewById(R.id.textMostraEmail);
		textMostraIdade = (TextView) findViewById(R.id.textMostraIdade);
		textMostraPeso = (TextView) findViewById(R.id.textMostraPeso);
		textMostraAltura = (TextView) findViewById(R.id.textMostraAltura);
		textMostraGenero = (TextView) findViewById(R.id.textMostraSexo);

		PerfilDAO dao = new PerfilDAO(this);
		Perfil perfil = dao.getPerfil();
		dao.close();
		if (perfil.getIdPerfil() != null) {
			textMostraNome.setText("NOME: " + perfil.getNome());
			textMostraEmail.setText("E-MAIL: " + perfil.getEmail());
			textMostraIdade.setText("IDADE: " + Integer.toString(perfil.getIdade()));
			textMostraPeso.setText("PESO(Kg): " + Integer.toString(perfil.getPeso()));
			textMostraAltura.setText("ALTURA(cm): " + Integer.toString(perfil.getAltura()));
			textMostraGenero.setText("GENERO: " + perfil.getGenero());
			
		} else {
			Toast.makeText(
					getBaseContext(),
					"Não há Perfil Cadastrado,"
							+ " clique em Editar Perfil para cadastrar um Perfil!",
					Toast.LENGTH_LONG).show();
			
		}

		criaBotoes();
	}

	public void criaBotoes() {
		buttonEditarPerfil = (Button) findViewById(R.id.buttonEditarPerfil);
		buttonEditarPerfil.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(TelaPerfilActivity.this,
						TelaEditarPerfilActivity.class);
				startActivity(intent);

			}
		});
		buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
		buttonVoltar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(TelaPerfilActivity.this,
						TelaMenuActivity.class);
				startActivity(intent);
			}
		});

	}
}

