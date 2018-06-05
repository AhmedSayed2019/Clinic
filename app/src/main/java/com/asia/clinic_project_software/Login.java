package com.asia.clinic_project_software;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private static EditText Email;
    private static EditText password;
    private static Button singin;
    private static ProgressBar progressBar;


    // public static final String URL_LOGIN = "http://10.0.2.2/sw/login.php";
     private static final String URL_LOGIN = "https://speedless-expiratio.000webhostapp.com/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isLoggedIn();
         progressBar=findViewById(R.id.progressBar);
        Email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.password);
        singin=(Button) findViewById(R.id.login);

    }

    public void signInNow(View view) {
        progressBar.setVisibility(View.VISIBLE);

        StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("success"))
                {


                    // firstly save shared preference
                    SharedPreferences share = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("Email", Email.getText().toString());
                    editor.putBoolean("loggedIn", true);
                    editor.apply();
                    editor.commit(); // commit changes


                    // go to main  activity
                    Intent bus=new Intent(Login.this,MainActivity.class);
                    startActivity(bus);


                }
                else
                {
                    Toast.makeText(Login.this, "Email or password is wrong ", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", Email.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        progressBar.setVisibility(View.GONE);
    }
    public void isLoggedIn()
    {
        SharedPreferences share = this.getSharedPreferences("login", MODE_PRIVATE);
        boolean logged = share.getBoolean("loggedIn", false);
        if(logged == true){
            startActivity(new Intent(Login.this, MainActivity.class));
        }

    }


    public void goToSignUp(View view) {
        Intent bus=new Intent(Login.this,SignUp.class);
        startActivity(bus);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;

    }
}
