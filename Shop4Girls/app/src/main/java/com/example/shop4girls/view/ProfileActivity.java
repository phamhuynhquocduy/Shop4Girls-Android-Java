package com.example.shop4girls.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity {

    private EditText edtFirstName,edtLastName,edtAddress,edtEmail,edtPhone;
    private TextInputLayout tilFirstName,tilLastName,tilAddress,tilEmail,tilPhone;
    private Button btnSave;
    private Toolbar toolbar;
    private RadioButton radioButtonMale, radioButtonFeMale;
    private int sex=0;
    public static final String EMAIL_PATTERN="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    public static final String PHONE_PATTERN="^0[0-9]{9,10}$";
    private boolean checkAddress=true, checkLastName=true, checkFirstName=true , checkPhone=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtAddress = findViewById(R.id.edittext_address);
        edtFirstName = findViewById(R.id.edittext_firstname);
        edtLastName =findViewById(R.id.edittext_lastname);
        edtEmail = findViewById(R.id.edittext_email);
        edtPhone = findViewById(R.id.edittext_phone);
        btnSave = findViewById(R. id.button_edit_profile);
        toolbar = findViewById(R.id.toolbar);
        radioButtonFeMale = findViewById(R.id.radiobutton_female);
        radioButtonMale = findViewById(R.id.radiobutton_male);
        tilAddress = findViewById(R.id.til_address);
        tilFirstName = findViewById(R.id.til_first_name);
        tilLastName = findViewById(R.id.til_last_name);
        tilEmail = findViewById(R.id.til_email);
        tilPhone = findViewById(R.id.til_phone);

        setActionBar();
        eventButtonSave();
        checkInput();
        getData();
    }
    private void checkError(){
        if(checkAddress==true&&checkLastName==true&&checkFirstName==true&&checkPhone==true){
            btnSave.setEnabled(true);
        }else {
            btnSave.setEnabled(false);
        }

    }

    private void eventButtonSave() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ProfileActivity.this);
                //Content view for dialog
                dialog.setContentView(R.layout.layout_custom_dialog);
                //Connect XML layout and Java code
                final Button bntAccept = dialog.findViewById(R.id.btn_accept);
                final  Button bntDismiss =dialog.findViewById(R.id.bnt_dismiss);
                final ImageButton imgBntDismiss =dialog.findViewById(R.id.img_btn_dismiss);
                bntDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Dismiss Dialog
                        dialog.dismiss();
                    }
                });
                imgBntDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                bntAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateProfile();
                        dialog.cancel();
                    }

                });
                // Set width and height
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData(){
        edtFirstName.setText(MainActivity.account.getFirstName());
        edtLastName.setText(MainActivity.account.getLastName());
        edtPhone.setText(MainActivity.account.getPhone());
        edtEmail.setText(MainActivity.account.getEmail());
        edtAddress.setText(MainActivity.account.getAddress());
        sex=MainActivity.account.getSex();
        if(sex==0){
            radioButtonMale.setChecked(true);
            radioButtonFeMale.setChecked(false);
        }else{
            radioButtonFeMale.setChecked(true);
            radioButtonMale.setChecked(false);
        }
    }

    private void updateProfile(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.updateProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")) {
                    MainActivity.account.setLastName(edtLastName.getText().toString().trim());
                    MainActivity.account.setFirstName(edtFirstName.getText().toString().trim());
                    MainActivity.account.setPhone(edtPhone.getText().toString().trim());
                    MainActivity.account.setAddress(edtAddress.getText().toString().trim());
                    if(radioButtonMale.isChecked()){
                        MainActivity.account.setSex(0);
                    }else{
                        MainActivity.account.setSex(1);
                    }
                    MainActivity.account.setPass(edtLastName.getText().toString().trim());
                    Toast.makeText(ProfileActivity.this, "Cập nhật tài khoản thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ProfileActivity.this, "Cập nhật tài khoản không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this,"Lỗi Xảy Ra: " +error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("id", String.valueOf(LoginActivity.id));
                param.put("ho",edtLastName.getText().toString().trim());
                param.put("email",edtEmail.getText().toString().trim());
                param.put("ten",edtFirstName.getText().toString().trim());
                param.put("dt",edtPhone.getText().toString().trim());
                param.put("diachi",edtAddress.getText().toString().trim());
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
        edtFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtLastName.getText().length()<= 0) {
                    tilLastName.setError("Không được để trống");
                    btnSave.setEnabled(false);
                    checkLastName=false;
                }
                else {
                    tilLastName.setError(null);
                    checkLastName=true;
                    checkError();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern patternDate = Pattern.compile(PHONE_PATTERN,Pattern.CASE_INSENSITIVE);
                Matcher matcher = patternDate.matcher(edtPhone.getText().toString().trim());
                if (edtPhone.getText().length()<= 0) {
                    tilPhone.setError("Không được để trống");
                    btnSave.setEnabled(false);
                    checkPhone=false;
                }
                else if(!matcher.matches()){
                    tilPhone.setError("Số điện thoại không hợp lệ");
                    btnSave.setEnabled(false);
                    checkPhone=false;
                }
                else {
                    tilPhone.setError(null);
                    checkPhone=true;
                    checkError();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtAddress.getText().length()<= 0) {
                    tilAddress.setError("Không được để trống");
                    btnSave.setEnabled(false);
                    checkAddress=false;
                }
                else {
                    tilAddress.setError(null);
                    checkAddress=true;
                    checkError();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

}