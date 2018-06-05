package com.asia.clinic_project_software;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class SignUp extends AppCompatActivity {
    private static EditText name;
    private static EditText phone;
    private static EditText Email;
    private static EditText password;

    private static RadioGroup gender ;
    private static RadioButton male;
    private static RadioButton female;

    private String genderType;

    private ProgressBar progressBar;

    // public static final String URL_ADD = "http://10.0.2.2/sw/signUp.php";
    private static final String URL_ADD = "https://speedless-expiratio.000webhostapp.com/signUp.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=(EditText) findViewById(R.id.name);
        phone=(EditText) findViewById(R.id.phone);
        Email=(EditText) findViewById(R.id.Email);
        password=(EditText) findViewById(R.id.password);
        gender=(RadioGroup)findViewById(R.id.gender);
        male=(RadioButton) findViewById(R.id.male);
        female=(RadioButton) findViewById(R.id.female);

        progressBar=findViewById(R.id.progressBar);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId)
                {
                    case R.id.male :
                        genderType="male";
                        break;
                    case  R.id.female:
                        genderType="female";
                        break;
                }
            }
        });
    }

    public void goToSignIn(View view) {
        Intent bus =new Intent(SignUp.this,Login.class);
        startActivity(bus);
    }
    public void signInNow(View view) {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, URL_ADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("success")){
                    Toast.makeText(SignUp.this, "2", Toast.LENGTH_SHORT).show();
                    // firstly save shared preference
                    SharedPreferences share = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("Email", Email.getText().toString());
                    editor.putBoolean("loggedIn", true);
                    // go to main activity
                    Intent bus = new Intent(SignUp.this, MainActivity.class);
                    startActivity(bus);
                    editor.apply();

                }else{
                    Toast.makeText(SignUp.this, response, Toast.LENGTH_SHORT).show();
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
                params.put("name", name.getText().toString());
                params.put("phone", phone.getText().toString());
                params.put("gender", genderType);
                params.put("email", Email.getText().toString());
                params.put("password", password.getText().toString());
                return params;

            }
//            @Override
//            protected Map<String, Image> getParams1() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//
//                return params;
//
//            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        progressBar.setVisibility(View.GONE);
    }
}
