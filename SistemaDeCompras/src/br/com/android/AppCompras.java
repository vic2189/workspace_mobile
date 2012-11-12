package br.com.android;

import android.os.Bundle;
import android.widget.*;
import android.app.*;
import android.view.*;

public class AppCompras extends Activity {

	CheckBox chkarroz,chkleite,chkcarne,chkfeijao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_compras);
    
        chkarroz = (CheckBox) findViewById(R.id.chkarroz);
        chkleite = (CheckBox) findViewById(R.id.chkleite);
        chkcarne = (CheckBox) findViewById(R.id.chkcarne);
        chkfeijao= (CheckBox) findViewById(R.id.chkfeijao);
        
        Button btbotao =(Button) findViewById(R.id.rbtotal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_app_compras, menu);
        return true;
    }
}
