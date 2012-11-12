package br.com.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.view.*;
import android.app.*;

public class AppSoma extends Activity {
	
	EditText ednumero1,ednumero2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_soma);
        
        ednumero1 = (EditText) findViewById(R.id.numero1);
        ednumero2 = (EditText) findViewById(R.id.numero2);
        Button btsomar = (Button) findViewById(R.id.btsomar);
        btsomar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				double numero1 =
					Double.parseDouble(ednumero1.getText().toString());
					double numero2 =
					Double.parseDouble(ednumero2.getText().toString());
				double resultadoDaSoma = numero1 + numero2;
				AlertDialog.Builder dialogo = new
				AlertDialog.Builder(AppSoma.this);
				dialogo.setTitle("Aviso");
				dialogo.setMessage("Soma:" + resultadoDaSoma);
				dialogo.setNeutralButton("OK", null);
				dialogo.show();								
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_app_soma, menu);
        return true;
    }
}
