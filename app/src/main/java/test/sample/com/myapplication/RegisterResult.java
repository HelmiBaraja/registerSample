package test.sample.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_result);

        Bundle extras = getIntent().getExtras();

        String name = extras.getString("my_name");
        String emailAddress = extras.getString("email_address");

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("My name is "+name+" and you can contact me at : "+emailAddress);
    }
}
