package br.com.tcc.android;

import android.os.Bundle;
import android.app.Activity;


public class MainActivity extends Activity {//Toda classe para aplicação Android,deve derivada da classe Activity (Atividade)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Invoca método onCreate da super classe,passando parâmetro (savedInstanceState)
        setContentView(R.layout.activity_menu);//Exibir a tela da aplicação
    }    
}
