package br.com.tcc.android;

import java.util.List;

import br.com.tcc.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.*;
import br.com.tcc.android.Perfil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class TelaEditarPerfilActivity extends Activity {

	EditText editTextNome, editTextEmail, editTextIdade, editTextAltura,
			editTextPeso;
	Spinner spinnerGenero;
	private Button buttonSalvar;
	private Button buttonCancelar;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_perfil);
		
		// Preenche Opcao de Sexo		
		Spinner spinner = (Spinner) findViewById(R.id.spinnerGenero);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.sexo_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		// Valores da Tela
		editTextNome = (EditText) findViewById(R.id.editTextNome);
		editTextEmail = (EditText) findViewById(R.id.editTextEmail);
		editTextIdade = (EditText) findViewById(R.id.editTextIdade);
		editTextAltura = (EditText) findViewById(R.id.editTextAltura);
		editTextPeso = (EditText) findViewById(R.id.editTextPeso);
		
		Bundle extra = getIntent().getExtras();
		int idPerfil = Integer.parseInt(extra.getString("perfil"));
		PerfilDAO dao = new PerfilDAO(this);
		List<Perfil> perfil = dao.buscaPerfil(idPerfil);
		if(perfil.isEmpty()){
			editTextNome = setText(perfil.get(0).getNome());
			editTextEmail = setText(perfil.get(0).getEmail());
			editTextIdade = setText(perfil.get(0).getIdade());
			editTextAltura = setText(perfil.get(0).getAltura());
			editTextPeso = setText(perfil.get(0).getPeso());
		}
				
		criaBotao();

	}

	public void criaBotao() {
		buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
		buttonSalvar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Perfil dadosPerfil = new Perfil();
				/*DESCOMENTAR O TRECHO PARA TESTAR NO CELULAR
				TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
				dadosPerfil.setIdPerfil(Integer.parseInt(telephonyManager.getDeviceId()));
				*/
				dadosPerfil.setIdPerfil(1);				
				dadosPerfil.setNome(editTextNome.getEditableText().toString());				
				dadosPerfil.setEmail(editTextEmail.getEditableText().toString());				
				String idade = editTextIdade.getText().toString();
				dadosPerfil.setIdade(Integer.parseInt(idade));				
				String altura = editTextAltura.getText().toString();
				dadosPerfil.setAltura(Integer.parseInt(altura));				
				String peso = editTextPeso.getText().toString();
				dadosPerfil.setPeso(Integer.parseInt(peso));				
				spinnerGenero = (Spinner) findViewById(R.id.spinnerGenero);
				switch(spinnerGenero.getSelectedItemPosition()) {
				case 0: dadosPerfil.setGenero("Feminino");
				case 1: dadosPerfil.setGenero("Masculino");
				}
				PerfilDAO dao = new PerfilDAO(TelaEditarPerfilActivity.this);
				dao.adicionar(dadosPerfil);
				dao.close();

				Intent intent = new Intent(TelaEditarPerfilActivity.this,
						TelaMenuActivity.class);
				startActivity(intent);

			}
		});

		buttonCancelar = (Button) findViewById(R.id.buttonCancelar);
		buttonCancelar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(TelaEditarPerfilActivity.this,
						TelaPerfilActivity.class);
				startActivity(intent);
			}
		});

	}
}
