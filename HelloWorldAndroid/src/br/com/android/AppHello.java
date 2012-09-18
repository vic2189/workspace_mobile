package br.com.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AppHello extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_hello);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_app_hello, menu);
        return true;
    }
}
