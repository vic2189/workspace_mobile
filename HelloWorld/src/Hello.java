
import android.app.Activity;  
import android.os.Bundle;  
import android.widget.TextView;  
  
public class HelloWorld extends Activity {  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        TextView view = new TextView(this);  
        view.setText("Hello, Android");  
        setContentView(view);  
  
    }  
}  