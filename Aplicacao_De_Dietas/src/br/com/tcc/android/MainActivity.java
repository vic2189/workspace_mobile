package br.com.tcc.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {//Toda classe para aplica��o Android,deve derivada da classe Activity (Atividade)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Invoca m�todo onCreate da super classe,passando par�metro (savedInstanceState)
        setContentView(R.layout.activity_menu);//Exibir a tela da aplica��o
    }    
}
