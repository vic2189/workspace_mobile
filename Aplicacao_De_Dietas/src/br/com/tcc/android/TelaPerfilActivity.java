package br.com.tcc.android;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class TelaPerfilActivity extends Activity {

	EditText textMostraNome, textMostraEmail, textMostraIdade, textMostraAltura,
	textMostraPeso,textMostraGenero;
	private Button buttonEditarPerfil;
	private Button buttonVoltar;
	private List<Perfil> perfil;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);
		textMostraNome = (EditText) findViewById(R.id.textMostraNome);
		textMostraEmail = (EditText) findViewById(R.id.textMostraEmail);
		textMostraIdade = (EditText) findViewById(R.id.textMostraIdade);
		textMostraPeso = (EditText) findViewById(R.id.textMostraPeso);
		textMostraAltura = (EditText) findViewById(R.id.textMostraAltura);
		textMostraGenero = (EditText) findViewById(R.id.textMostraSexo);
		
		PerfilDAO dao = new PerfilDAO(this);
		this.perfil = dao.getLista();
		dao.close();
		if(perfil.isEmpty()){
			textMostraNome.setText(perfil.get(0).getNome());
			textMostraEmail.setText(perfil.get(0).getEmail());
			textMostraIdade.setText(perfil.get(0).getIdade());
			textMostraPeso.setText(perfil.get(0).getPeso());
			textMostraAltura.setText(perfil.get(0).getAltura());
			textMostraGenero.setText(perfil.get(0).getGenero());

		}else{
			textMostraNome.setText("Não a Perfil Cadastrado");
			textMostraEmail.setText("Clique em Editar Perfil para cadastrar um perfil");
		}
		
		criaBotao();
	}

	public void criaBotao() {
		buttonEditarPerfil = (Button) findViewById(R.id.buttonEditarPerfil);
		buttonEditarPerfil.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(TelaPerfilActivity.this,
						TelaEditarPerfilActivity.class);
				Bundle extras = new Bundle();
				extras.putInt("perfil", perfil.get(0).getIdPerfil());
				intent.putExtras(extras);
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
