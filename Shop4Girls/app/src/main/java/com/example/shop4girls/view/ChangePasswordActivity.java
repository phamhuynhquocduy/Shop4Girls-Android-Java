package com.example.shop4girls.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

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
import com.example.shop4girls.connect.Server;
import com.google.android.material.textfield.TextInputLayout;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText edtOldPass, edtNewPass , edtConfirm;
    private TextInputLayout tilOldPass , tilNewPass, tilConfirm;
    private Button btnAccept;
    private TextView passwordStrenghText;
    private ProgressBar passwordStrenghProgressBar;
    private Boolean checkOldPass=false , checkNewPass=false , checkConfirm=false;
    public static  final String PASS_PATTERN="^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*+=?/-]).{8,15}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        toolbar = findViewById(R.id.toolbar);
        edtConfirm = findViewById(R.id.txt_confirm_new_password);
        edtOldPass = findViewById(R.id.txt_old_password);
        edtNewPass = findViewById(R.id.txt_new_password);
        btnAccept = findViewById(R.id.button_change_password);
        tilConfirm = findViewById(R.id.confirm_new_pass_word);
        tilNewPass =findViewById(R.id.new_pass_word);
        tilOldPass = findViewById(R.id.old_pass_word);
        passwordStrenghText = findViewById(R.id.textView);
        passwordStrenghProgressBar = findViewById(R.id.progressBar);
        passwordStrenghProgressBar.setVisibility(View.GONE);
        passwordStrenghText.setVisibility(View.GONE);
        btnAccept.setEnabled(false);

        checkInput();
        setActionBar();
        eventButtonChangePass();
    }

    private void eventButtonChangePass() {
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = md5(edtOldPass.getText().toString().trim());
                String checkPass =MainActivity.account.getPass();
                if(oldPass.equals(checkPass)){
                    tilOldPass.setError(null);
                    changePass();
                }else {
                    tilOldPass.setError("Mật khẩu không đúng");
                }
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
                    MainActivity.account.setPass(md5(edtNewPass.getText().toString().trim()));
                    Toast.makeText(getApplicationContext(), "Cập nhật tài khoản thành công"+LoginActivity.id, Toast.LENGTH_SHORT).show();
                    onBackPressed();
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
                param.put("id", String.valueOf(LoginActivity.id));
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

                Pattern patternDate = Pattern.compile(PASS_PATTERN,Pattern.CASE_INSENSITIVE);
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
                Pattern patternDate = Pattern.compile(PASS_PATTERN,Pattern.CASE_INSENSITIVE);
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
        edtOldPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Pattern patternDate = Pattern.compile(PASS_PATTERN,Pattern.CASE_INSENSITIVE);
                Matcher matcher = patternDate.matcher(edtOldPass.getText().toString().trim());
                if (edtOldPass.getText().length()<= 0) {
                    tilOldPass.setError("Không được để trống");
                    checkOldPass=false;
                    btnAccept.setEnabled(false);
                }
                else if(!matcher.matches()){
                    tilOldPass.setError("Mật mẩu phải từ 8-20 ký tự và chứa ít nhất chữ hoa, thường, ký tự đặc biệt");
                    checkOldPass=false;
                    btnAccept.setEnabled(false);
                }
                else {
                    tilOldPass.setError(null);
                    checkOldPass=true;
                    checkError();
                }
            }
        });
    }

    private void checkError(){
        if(checkConfirm==true&&checkNewPass==true&&checkOldPass==true){
            btnAccept.setEnabled(true);
        }else {
            btnAccept.setEnabled(false);
        }
    }

    private void setActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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