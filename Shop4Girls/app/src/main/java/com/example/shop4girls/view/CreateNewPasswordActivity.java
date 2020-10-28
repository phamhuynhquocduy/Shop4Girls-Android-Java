package com.example.shop4girls.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewPasswordActivity extends AppCompatActivity {
    private EditText edtConfirm, edtNewPass;
    private Button btnAccept;
    private TextInputLayout tilConfirm,tilNewPass;
    private Boolean checkConfirm=false,checkNewPass=false;
    private TextView passwordStrenghText;
    private ProgressBar passwordStrenghProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_password);

        btnAccept = findViewById(R.id.button_change_password);
        edtConfirm = findViewById(R.id.txt_confirm_new_password);
        edtNewPass = findViewById(R.id.txt_new_password);
        tilNewPass = findViewById(R.id.new_pass_word);
        tilConfirm = findViewById(R.id.confirm_new_pass_word);
        passwordStrenghText = findViewById(R.id.textView);
        passwordStrenghProgressBar = findViewById(R.id.progressBar);

        checkInput();
        eventButton();
    }

    private void eventButton() {
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePass();
            }
        });
    }

    private void changePass(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.changePassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String check = response;
                if(check.equals("success")) {
                    Toast.makeText(getApplicationContext(), "Cập nhật tài khoản thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Cập nhật tài khoản không thành công", Toast.LENGTH_SHORT).show();
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
                param.put("id", String.valueOf(ForgotPasswordActivity.id));
                param.put("password", edtNewPass.getText().toString().trim());
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void checkInput() {
        edtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newPass = edtNewPass.getText().toString().trim();
                String confirmPass = edtConfirm.getText().toString().trim();

                Pattern patternDate = Pattern.compile(SignInActivity.PASS_PATTERN,Pattern.CASE_INSENSITIVE);
                Matcher matcher = patternDate.matcher(edtConfirm.getText().toString().trim());
                if (edtConfirm.getText().length()<= 0) {
                    tilConfirm.setError("Không được để trống");
                    checkConfirm=false;
                    btnAccept.setEnabled(false);
                }
                else if(!matcher.matches()){
                    tilConfirm.setError("Mật mẩu phải từ 8-20 ký tự và chứa ít nhất chữ hoa, thường, ký tự đặc biệt");
                    checkConfirm=false;
                    btnAccept.setEnabled(false);
                } else if(!confirmPass.equals(newPass)&&newPass.length()>0){
                    tilConfirm.setError("Mật khẩu xác nhận không giống với mật khẩu mới");
                    checkConfirm=false;
                    btnAccept.setEnabled(false);
                }
                else {
                    tilConfirm.setError(null);
                    checkConfirm=true;
                    checkError();
                }
            }
        });
        edtNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Pattern patternDate = Pattern.compile(SignInActivity.PASS_PATTERN,Pattern.CASE_INSENSITIVE);
                Matcher matcher = patternDate.matcher(edtNewPass.getText().toString().trim());
                if (edtNewPass.getText().length()<= 0) {
                    passwordStrenghProgressBar.setVisibility(View.GONE);
                    passwordStrenghText.setVisibility(View.GONE);
                    tilNewPass.setError("Không được để trống");
                    checkNewPass=false;
                    btnAccept.setEnabled(false);
                }
                else if(!matcher.matches()){
                    passwordStrenghProgressBar.setVisibility(View.GONE);
                    passwordStrenghText.setVisibility(View.GONE);
                    tilNewPass.setError("Mật mẩu phải từ 8-20 ký tự và chứa ít nhất chữ hoa, thường, ký tự đặc biệt");
                    checkNewPass=false;
                    btnAccept.setEnabled(false);
                }
                else {
                    passwordStrenghProgressBar.setVisibility(View.VISIBLE);
                    passwordStrenghText.setVisibility(View.VISIBLE);
                    measurePasswordStrengh(edtNewPass.getText().toString().trim());
                    tilNewPass.setError(null);
                    checkNewPass=true;
                    checkError();
                }
            }
        });
    }

    private void checkError(){
        if(checkConfirm==true&&checkNewPass==true){
            btnAccept.setEnabled(true);
        }else {
            btnAccept.setEnabled(false);
        }
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void measurePasswordStrengh(String password) {
        Zxcvbn zxcvbn = new Zxcvbn();
        Strength strength = zxcvbn.measure(password);
        int score = strength.getScore();
        passwordStrenghProgressBar.setProgress(score + 1);

        int color = ContextCompat.getColor(getApplicationContext(), android.R.color.darker_gray);
        switch (score) {
            case 0: {
                color = ContextCompat.getColor(getApplicationContext(), R.color.colorRed);
                passwordStrenghText.setText("Quá Yếu");
                break;
            }
            case 1: {
                color = ContextCompat.getColor(getApplicationContext(), R.color.colorOrange);
                passwordStrenghText.setText("Yếu");
                break;
            }
            case 2: {
                color = ContextCompat.getColor(getApplicationContext(), R.color.colorYellow);
                passwordStrenghText.setText("Trung Bình");
                break;
            }
            case 3: {
                color = ContextCompat.getColor(getApplicationContext(), R.color.colorBlue);
                passwordStrenghText.setText("Mạnh");
                break;
            }
            case 4: {
                color = ContextCompat.getColor(getApplicationContext(), R.color.colorGreen);
                passwordStrenghText.setText("Rất Mạnh");
                break;
            }
        }

        passwordStrenghText.setTextColor(color);
        passwordStrenghProgressBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

}