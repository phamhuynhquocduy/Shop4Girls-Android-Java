package com.example.shop4girls.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4girls.R;
import com.example.shop4girls.connect.Server;

import java.util.HashMap;
import java.util.Map;

;

public class LoginActivity extends AppCompatActivity {
    private TextView txtNotification, txtSignIn;
    private EditText edtPass, edtEmail;
    private Button button;
    public  static  int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edittext_email);
        edtPass = findViewById(R.id.edittext_pass);
        button = findViewById(R.id.button_login);
        txtNotification = findViewById(R.id.textview_notification);
        txtSignIn =findViewById(R.id.txtSignIn);

        eventLogIn();
        clickTextSignIn();
    }

    private void eventLogIn() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAccount();
            }
        });
    }

    private void checkAccount(){
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getAccount, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("error")) {
                        txtNotification.setText("Sai mật khẩu hoặc tài khoản");
                    }else{
                        txtNotification.setText("");
                        id= Integer.parseInt(response);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this,"Lỗi Xảy Ra: " +error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("taikhoan",edtEmail.getText().toString().trim());
                    param.put("matkhau",edtPass.getText().toString().trim());
                    return param;
                }
            };
            requestQueue.add(stringRequest);
        }

    private void clickTextSignIn(){
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
            }
        });
    }
}