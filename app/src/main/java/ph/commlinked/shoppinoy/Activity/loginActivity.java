package ph.commlinked.shoppinoy.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import ph.commlinked.shoppinoy.R;

public class loginActivity extends AppCompatActivity{

    EditText username;
    EditText password;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.input_username);
        password = (EditText)findViewById(R.id.input_password);
        extras = getIntent().getExtras();

    }

}
