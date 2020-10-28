package com.example.shop4girls.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4girls.R;
import com.example.shop4girls.connect.SaveSharedPreference;
import com.example.shop4girls.connect.Server;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText edtEmail;
    private Button btnSave;
    private TextInputLayout tilEmail;
    private Boolean checkEmail=false;
    public  static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtEmail = findViewById(R.id.txt_forgot_password);
        btnSave = findViewById(R.id.button_forgot_password);
        tilEmail = findViewById(R.id.pass_word);
        btnSave.setEnabled(false);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmail();
            }
        });

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Pattern patternDate = Pattern.compile(SignInActivity.EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
                Matcher matcher = patternDate.matcher(edtEmail.getText().toString().trim());
                if (edtEmail.getText().length()<= 0) {
                    tilEmail.setError("Không được để trống");
                    btnSave.setEnabled(false);
                    checkEmail=false;
                }
                else if(!matcher.matches()){
                    tilEmail.setError("Email không hợp lệ");
                    btnSave.setEnabled(false);
                    checkEmail=false;
                }
                else {
                    tilEmail.setError(null);
                    checkEmail=true;
                    checkError();
                }
            }
        });
    }
    //Check Enable Button
    private void checkError() {
        if(checkEmail==true){
            btnSave.setEnabled(true);
        }else {
            btnSave.setEnabled(false);
        }
    }
    //Dialog
    private void eventDialog(){
        //Create random
        Random rNo = new Random();
        final int code = rNo.nextInt((99999 - 10000) + 1) + 10000;
        //Send Email
        checkAccount(code,edtEmail.getText().toString().trim());
        //Create Dialog
        final Dialog dialog = new Dialog(ForgotPasswordActivity.this);
        //Gan content view cho dialog la mot layout tu dinh nghia
        dialog.setContentView(R.layout.layout_custom_dialog_verify);
        //Ket noi XML layout va Java code
        final EditText txtUser = dialog.findViewById(R.id.txt_vetify);
        Button button = dialog.findViewById(R.id.btnClose);
        ImageButton buttonDismiss = dialog.findViewById(R.id.img_btn_dismiss);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tat Dialog
                String user =txtUser.getText().toString().trim();
                if(String.valueOf(code).equals(user)){
                    startActivity(new Intent(getApplicationContext(),CreateNewPasswordActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Mã Xác Nhận Không Đúng", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        buttonDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // Setting wight and height dialog
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
    //Send Email
    private void checkAccount(final int randomNumber, final String email){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.sendEmail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String check="success";
                if(check.equals(check)) {
                    Toast.makeText(getApplicationContext(),"Gửi Email thành công ",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Gửi Email không thành công",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Lỗi Xảy Ra: " +error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("random", String.valueOf(randomNumber));
                param.put("email",email);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    //Check Account
    private void checkEmail(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.checkAccount, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("error")) {
                    Toast.makeText(ForgotPasswordActivity.this, "Email không tồn tại trong tài khoản", Toast.LENGTH_SHORT).show();
                }else{
                    id= Integer.parseInt(response);
                    eventDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ForgotPasswordActivity.this,"Lỗi Xảy Ra: " +error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("taikhoan",edtEmail.getText().toString().trim());
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

}