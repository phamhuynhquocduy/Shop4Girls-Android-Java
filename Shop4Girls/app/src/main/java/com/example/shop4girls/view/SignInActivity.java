package com.example.shop4girls.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    private EditText edtFirstName,edtLastName,edtAddress,edtEmail,edtPhone,edtPass;
    private TextInputLayout tilFirstName,tilLastName,tilAddress,tilEmail,tilPhone,tilPass;
    private Button btnSave;
    private Toolbar toolbar;
    private RadioButton radioButtonMale, radioButtonFeMale;
    private int sex=0;
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
        checkInput();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
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
                }else{
                    Toast.makeText(SignInActivity.this, response+"", Toast.LENGTH_SHORT).show();
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
                }
                else if(!matcher.matches()){
                    tilEmail.setError("Email không hợp lệ");
                    btnSave.setEnabled(false);
                }
                else {
                    tilEmail.setError(null);
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
                }
                else {
                    tilFirstName.setError(null);
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
                    btnSave.setEnabled(false);
                }
                else {
                    tilLastName.setError(null);
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
                    btnSave.setEnabled(false);
                }
                else if(!matcher.matches()){
                    tilPhone.setError("Số điện thoại không hợp lệ");
                    btnSave.setEnabled(false);
                }
                else {
                    tilPhone.setError(null);
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
                    btnSave.setEnabled(false);
                }
                else {
                    tilAddress.setError(null);
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
                Pattern patternDate = Pattern.compile(PASS_PATTERN,Pattern.CASE_INSENSITIVE);
                Matcher matcher = patternDate.matcher(edtPass.getText().toString().trim());
                if (edtPass.getText().length()<= 0) {
                    tilPass.setError("Không được để trống");
                    btnSave.setEnabled(false);
                }
                else if(!matcher.matches()){
                    tilPass.setError("Mật mẩu phải từ 8-20 ký tự và chứa ít nhất chữ hoa, thường, ký tự đặc biệt");
                    btnSave.setEnabled(false);
                }
                else {
                    tilPass.setError(null);
                    checkError();
                }
            }
        });
    }

    private void checkError(){
        if(tilAddress.getError()==null&&tilEmail.getError()==null&&tilFirstName.getError()==null&&tilPhone.getError()==null&&tilLastName.getError()==null){
            btnSave.setEnabled(true);
        }else {
            btnSave.setEnabled(false);
        }
    }
}