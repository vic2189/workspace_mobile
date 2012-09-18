package br.com.victorjordao.hello_world;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class HelloWorld extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView view = new TextView(this);
        view.setText("Hello, Android");
        setContentView(view);
    }

   

    
}
