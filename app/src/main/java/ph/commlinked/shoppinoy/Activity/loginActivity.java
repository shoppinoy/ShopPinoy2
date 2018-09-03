package ph.commlinked.shoppinoy.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import ph.commlinked.shoppinoy.R;

public class loginActivity extends AppCompatActivity{

    EditText username;
    EditText password;
    Button btnLogin;
    Bundle extras;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String UserHolder,PasswordHolder;
    Boolean CheckEditText;
    String HttpUrl = "https://empoweringfilipinos.ph/androidResource/androidLogin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Assigning ID's to EditText.
        username = (EditText)findViewById(R.id.input_username);
        password = (EditText)findViewById(R.id.input_password);
        // Assigning ID's to Button.
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){
                    userLogin();
                }else{
                    Toast.makeText(getApplicationContext(), "User credentials does not exist.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Assigning Activity this to progress dialog.
        progressDialog = new ProgressDialog(loginActivity.this);

    }
    public void CheckEditTextIsEmptyOrNot() {

        // Getting values from EditText.
        UserHolder = username.getText().toString().trim();
        PasswordHolder = password.getText().toString().trim();

        // Checking whether EditText value is empty or not.
        if (TextUtils.isEmpty(UserHolder) || TextUtils.isEmpty(PasswordHolder)) {

            // If any of EditText is empty then set variable value as False.
            CheckEditText = false;

        } else {

            // If any of EditText is filled then set variable value as True.
            CheckEditText = true;
        }
    }


    public void userLogin(){
        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Matching server responce message to our text.
                        if(ServerResponse.equalsIgnoreCase("Data Matched")) {

                            // If response matched then show the toast.
                            Toast.makeText(loginActivity.this, "Logged In Successfully", Toast.LENGTH_LONG).show();

                            // Finish the current Login activity.
                            finish();

                            // Opening the user profile activity using intent.
                            //Intent intent = new Intent(loginActivity.this, loginActivity.class);

                            // Sending User Email to another activity using intent.
                            //intent.putExtra("UsernameTag", UserHolder);

                            //startActivity(intent);
                        }
                        else {

                            // Showing Echo Response Message Coming From Server.
                            Toast.makeText(loginActivity.this, ServerResponse, Toast.LENGTH_LONG).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(loginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("Username", UserHolder);
                params.put("Password", PasswordHolder);

                return params;
            }

        };

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(loginActivity.this);
        requestQueue.add(stringRequest);


    }

}
