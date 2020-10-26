package com.example.shop4girls.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shop4girls.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.TooManyListenersException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText edtOldPass, edtNewPass , edtConfirm;
    private TextInputLayout tilOldPass , tilNewPass, tilConfirm;
    private Button btnAccept;
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
        btnAccept.setEnabled(false);

        checkInput();
        setActionBar();
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
                    tilNewPass.setError("Không được để trống");
                    checkNewPass=false;
                    btnAccept.setEnabled(false);
                }
                else if(!matcher.matches()){
                    tilNewPass.setError("Mật mẩu phải từ 8-20 ký tự và chứa ít nhất chữ hoa, thường, ký tự đặc biệt");
                    checkNewPass=false;
                    btnAccept.setEnabled(false);
                }
                else {
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
}