package com.example.shop4girls.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

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

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    private EditText edtFirstName,edtLastName,edtAddress,edtEmail,edtPhone,edtPass;
    private TextInputLayout tilFirstName,tilLastName,tilAddress,tilEmail,tilPhone,tilPass;
    private Button btnSave;
    private Toolbar toolbar;
    private RadioButton radioButtonMale, radioButtonFeMale;
    private TextView txtBackLogIn,passwordStrenghText;
    private int sex=0;
    private ProgressBar passwordStrenghProgressBar;
    private boolean checkFirstName=false,checkLastName=false,checkAddress=false,checkEmail=false,checkPhone=false,checkPass=false;
    public static final String EMAIL_PATTERN="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    public static final String PHONE_PATTERN="^0[0-9]{9,10}$";
    public static  final String PASS_PATTERN="^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*+=?/-]).{8,15}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtAddress = findViewById(R.id.edittext_address);
        edtFirstName = findViewById(R.id.edittext_firstname);
        edtLastName =findViewById(R.id.edittext_lastname);
        edtEmail = findViewById(R.id.edittext_email);
        edtPhone = findViewById(R.id.edittext_phone);
        btnSave = findViewById(R. id.button_signin);
        toolbar = findViewById(R.id.toolbar);
        radioButtonFeMale = findViewById(R.id.radiobutton_female);
        radioButtonMale = findViewById(R.id.radiobutton_male);
        edtPass = findViewById(R.id.edittext_pass);
        tilAddress = findViewById(R.id.til_address);
        tilFirstName = findViewById(R.id.til_first_name);
        tilLastName = findViewById(R.id.til_last_name);
        tilEmail = findViewById(R.id.til_email);
        tilPhone = findViewById(R.id.til_phone);
        tilPass = findViewById(R.id.til_pass);
        txtBackLogIn = findViewById(R.id.txt_sign_in);
        passwordStrenghText =findViewById(R.id.textView);
        passwordStrenghProgressBar = findViewById(R.id.progressBar);
        passwordStrenghProgressBar.setVisibility(View.GONE);
        passwordStrenghText.setVisibility(View.GONE);
        btnSave.setEnabled(false);

        checkInput();
        eventBackLogIn();
        eventSignIn();
    }

    private void eventSignIn() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSameAccount();
            }
        });
    }

    private void eventDialog(){
        //Create random
        Random rNo = new Random();
        final int code = rNo.nextInt((99999 - 10000) + 1) + 10000;
        //Send Email
        checkAccount(code,edtEmail.getText().toString().trim());
        //Create Dialog
        final Dialog dialog = new Dialog(SignInActivity.this);
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
                    createAccount();
                }else{
                    Toast.makeText(SignInActivity.this, "Mã Xác Nhận Không Đúng", Toast.LENGTH_SHORT).show();
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

    private void eventBackLogIn(){
        txtBackLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void createAccount(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.postAccount, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")) {
                    Toast.makeText(SignInActivity.this, "Đăng ký khoản thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SignInActivity.this, "Đăng ký không thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignInActivity.this,"Lỗi Xảy Ra: " +error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("ho",edtLastName.getText().toString().trim());
                param.put("email",edtEmail.getText().toString().trim());
                param.put("ten",edtFirstName.getText().toString().trim());
                param.put("dt",edtPhone.getText().toString().trim());
                param.put("diachi",edtAddress.getText().toString().trim());
                param.put("matkhau",edtPass.getText().toString().trim());
                if(radioButtonMale.isChecked()){
                    param.put("gioitinh", String.valueOf(0));
                }else{
                    param.put("gioitinh", String.valueOf(1));
                }
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private  void checkInput(){
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Pattern patternDate = Pattern.compile(EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
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
        edtFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtFirstName.getText().length()<= 0) {
                    tilFirstName.setError("Không được để trống");
                    btnSave.setEnabled(false);
                    checkFirstName=false;
                }
                else {
                    tilFirstName.setError(null);
                    checkFirstName=true;
                    checkError();
                }
            }
        });
        edtLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtLastName.getText().length()<= 0) {
                    tilLastName.setError("Không được để trống");
                    checkLastName=false;
                    btnSave.setEnabled(false);
                }
                else {
                    tilLastName.setError(null);
                    checkLastName=true;
                    checkError();
                }
            }
        });
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Pattern patternDate = Pattern.compile(PHONE_PATTERN,Pattern.CASE_INSENSITIVE);
                Matcher matcher = patternDate.matcher(edtPhone.getText().toString().trim());
                if (edtPhone.getText().length()<= 0) {
                    tilPhone.setError("Không được để trống");
                    checkPhone=false;
                    btnSave.setEnabled(false);
                }
                else if(!matcher.matches()){
                    tilPhone.setError("Số điện thoại không hợp lệ");
                    checkPhone=false;
                    btnSave.setEnabled(false);
                }
                else {
                    tilPhone.setError(null);
                    checkPhone=true;
                    checkError();
                }
            }
        });
        edtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtAddress.getText().length()<= 0) {
                    tilAddress.setError("Không được để trống");
                    checkAddress=false;
                    btnSave.setEnabled(false);
                }
                else {
                    tilAddress.setError(null);
                    checkAddress=true;
                    checkError();
                }
            }
        });
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = edtFirstName.getText().toString().trim()+" "+edtLastName.getText().toString().trim();
                name = removeAccent(name);
                String pass = edtPass.getText().toString().trim();
                Pattern patternDate = Pattern.compile(PASS_PATTERN,Pattern.CASE_INSENSITIVE);
                Matcher matcher = patternDate.matcher(edtPass.getText().toString().trim());
                if (edtPass.getText().length()<= 0) {
                    passwordStrenghProgressBar.setVisibility(View.GONE);
                    passwordStrenghText.setVisibility(View.GONE);
                    tilPass.setError("Không được để trống");
                    checkPass=false;
                    btnSave.setEnabled(false);
                }
                else if(!matcher.matches()){
                    passwordStrenghProgressBar.setVisibility(View.GONE);
                    passwordStrenghText.setVisibility(View.GONE);
                    tilPass.setError("Mật mẩu phải từ 8 ký tự bao gồm chữ hoa, chữ thường, ký tự đặc biệt");
                    checkPass=false;
                    btnSave.setEnabled(false);
                }
                else {
                    passwordStrenghProgressBar.setVisibility(View.VISIBLE);
                    passwordStrenghText.setVisibility(View.VISIBLE);
                    measurePasswordStrengh(edtPass.getText().toString().trim());
                    tilPass.setError(null);
                    checkPass=true;
                    checkError();
                }
            }
        });
    }

    private void measurePasswordStrengh(String password) {
        String[] array = {"Duy", "duy", "Tuan", "tuan", "Pham","pham","Huynh","huynh","Ly","ly","Tran","Tran","Chau","chau","123123 ","Password ","123456 ","12345678 ","qwerty",
        "12345","123456789","hello","freedom","iloveyou","welcome","money","letmein","passw0rd","abc123","qazwsx","trustno1","asdf","test",""};
        List<String> list = Arrays.asList(array);
        Zxcvbn zxcvbn = new Zxcvbn();
        Strength strength = zxcvbn.measure(password,list);
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

    private void checkError(){
        if(checkFirstName==true&&checkLastName==true&&checkPass==true&&checkEmail==true&&checkAddress==true&&checkPhone==true){
            btnSave.setEnabled(true);
        }else {
            btnSave.setEnabled(false);
        }
    }

    private void checkAccount(final int randomNumber, final String email){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.sendEmail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String check="success";
                if(check.equals(check)) {
                    Toast.makeText(SignInActivity.this,"Gửi Email thành công ",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(SignInActivity.this,"Gửi Email không thành công",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignInActivity.this,"Lỗi Xảy Ra: " +error.getMessage(),Toast.LENGTH_LONG).show();
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

    private void checkSameAccount(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.checkSameAccount, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("error")) {
                    Toast.makeText(SignInActivity.this, "Tài Khoản Đã Tồn Tại", Toast.LENGTH_SHORT).show();
                }else {
                   eventDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignInActivity.this,"Lỗi Xảy Ra: " +error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("email",edtEmail.getText().toString().trim());
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private boolean convertString(String check,String password){
        String [] words = check.split(" ", 8);
        for (String word : words) {
            if (password.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return  false;
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

//    else if(convertString(name,pass)){
//        passwordStrenghProgressBar.setVisibility(View.GONE);
//        passwordStrenghText.setVisibility(View.GONE);
//        tilPass.setError("Mật mẩu không được chứa họ tên");
//        checkPass=false;
//        btnSave.setEnabled(false);
//    }
}