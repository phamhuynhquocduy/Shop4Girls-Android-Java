package com.example.shop4girls.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private EditText edtPass,edtFirstName,edtLastName,edtAddress,edtEmail,edtPhone;
    private Button btnSave;
    private Toolbar toolbar;
    private RadioButton radioButtonMale, radioButtonFeMale;
    private int sex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtAddress = findViewById(R.id.edittext_address);
        edtFirstName = findViewById(R.id.edittext_firstname);
        edtLastName =findViewById(R.id.edittext_lastname);
        edtEmail = findViewById(R.id.edittext_email);
        edtPhone = findViewById(R.id.edittext_phone);
        edtPass = findViewById(R.id.edittext_pass);
        btnSave = findViewById(R. id.button_edit_profile);
        toolbar = findViewById(R.id.toolbar);
        radioButtonFeMale = findViewById(R.id.radiobutton_female);
        radioButtonMale = findViewById(R.id.radiobutton_male);

        setActionBar();
        getData();
        eventButtonSave();
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
                        dialog.dismiss();
                    }
                });
                bntAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData(){
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.getProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    edtLastName.setText(jsonObject.getString("ho"));
                    edtFirstName.setText(jsonObject.getString("ten"));
                    edtEmail.setText(jsonObject.getString("email"));
                    edtPhone.setText(jsonObject.getString("dienthoai"));
                    edtAddress.setText(jsonObject.getString("diachi"));
                    sex=jsonObject.getInt("gioitinh");
                    if(sex==0){
                        radioButtonMale.isChecked();
                    }else{
                        radioButtonFeMale.isChecked();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ProfileActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, error.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String, String>();
                param.put("id",String.valueOf(LoginActivity.id));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

}