package br.com.tcc.android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class TelaEditarPerfilActivity extends Activity {

	private static final String GENERO_FEMININO = "Feminino";
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
		PerfilDAO dao = new PerfilDAO(this);
		Perfil perfil = dao.getPerfil();
		dao.close();
		if(perfil.getIdPerfil() != null){
			editTextNome.setText(perfil.getNome());
			editTextEmail.setText(perfil.getEmail());
			editTextIdade.setText(Integer.toString(perfil.getIdade()));
			editTextAltura.setText(Integer.toString(perfil.getAltura()));
			editTextPeso.setText(Integer.toString(perfil.getPeso()));
			spinner.setSelection(getPosicao(perfil));
		}

		criaBotao();

	}

	public void criaBotao() {
		buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
		buttonSalvar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				/*DESCOMENTAR O TRECHO PARA TESTAR NO CELULAR
				TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
				dadosPerfil.setIdPerfil(Integer.parseInt(telephonyManager.getDeviceId()));
				 */
				Perfil dadosPerfil = new Perfil();
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
				case 0: dadosPerfil.setGenero(GENERO_FEMININO);
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

	private int getPosicao(Perfil perfil) {
		if(GENERO_FEMININO.equalsIgnoreCase(perfil.getGenero())){
			return 0;
		}else{
			return 1;
		}
	}
}
