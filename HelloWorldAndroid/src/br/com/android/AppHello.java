package br.com.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/*toda classe de aplica��o Android derivada da classe Activity*/
public class AppHello extends Activity {
	

    @Override
    public void onCreate(Bundle savedInstanceState) { //Metodo Principal
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_hello);//Metodo responsavel pela exibi��o da tela de Apli.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_app_hello, menu);
        return true;
    }
}
