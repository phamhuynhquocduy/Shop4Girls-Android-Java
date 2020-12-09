package com.example.shop4girls.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4girls.R;
import com.example.shop4girls.model.Firm;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class DepositActivity extends AppCompatActivity {

    private EditText  edtRemainingCapacity, edtNameProduct,edtExpiryDate,edtDateManufacture,edtPrice, edtDescription;
    private TextInputLayout tilNameProduct,tilExpiryDate,tilDateManufacture,tilPrice, tilDescription,tilRemainingCapacity;
    private Boolean checkNameProduct=false,checkExpiryDate=false,checkDateManufacture=false,checkPrice=false, checkDescription=false,checkRemainingCapacity=false;
    private CheckBox checkBox;
    private Toolbar toolbar;
    private RadioButton radioButtonLipstick, radioButtonPerfume;
    private Button button;
    private Context context;
    private ImageButton imgBtnExpiryDate, imgBtnDateManufacture;
    private Spinner spinner;
    private ArrayAdapter<Firm> spinnerAdapter;
    ArrayList arrayList = new ArrayList();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        edtRemainingCapacity=findViewById(R.id.edt_remaining_capacity);
        checkBox=findViewById(R.id.checkBox);
        radioButtonLipstick=findViewById(R.id.radiobutton_lipstick);
        radioButtonPerfume=findViewById(R.id.radiobutton_perfume);
        tilRemainingCapacity = findViewById(R.id.til_remaining_capacity);
        button = findViewById(R.id.button_print);
        toolbar = findViewById(R.id.toolbar);
        edtNameProduct = findViewById(R.id.edt_name_prodcut);
        edtExpiryDate = findViewById(R.id.edt_expiry_date);
        edtDateManufacture = findViewById(R.id.edt_date_manufacture);
        imgBtnDateManufacture = findViewById(R.id.date_manufacture);
        imgBtnExpiryDate = findViewById(R.id.expiry_date);
        edtDescription = findViewById(R.id.edt_description);
        edtPrice = findViewById(R.id.edt_price);
        tilDescription = findViewById(R.id.til_description);
        tilNameProduct = findViewById(R.id.til_name_product);
        tilDateManufacture = findViewById(R.id.til_date_manufacture);
        tilExpiryDate = findViewById(R.id.til_expiry_date);
        tilPrice = findViewById(R.id.til_price);
        spinner = findViewById(R.id.spinner);
        loadFirm();
        eventImageButton();
        eventRadioButton();
        check();
        setSpinner();


        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BillDepositActivity.class);
                intent.putExtra("name",edtNameProduct.getText().toString().trim());
                intent.putExtra("DateManufacture",edtDateManufacture.getText().toString().trim());
                intent.putExtra("ExpiryDate",edtExpiryDate.getText().toString().trim());
                intent.putExtra("Description",edtDescription.getText().toString().trim());
                intent.putExtra("Price",edtPrice.getText().toString().trim());
                intent.putExtra("Firm",spinner.getSelectedItem().toString());
                if(radioButtonLipstick.isChecked()){
                    intent.putExtra("Type","Son");
                    intent.putExtra("TrangThai","Sản Phẩm Chưa Được Sử Dụng");
                }else{
                    intent.putExtra("Type","Nước Hoa");
                    intent.putExtra("TrangThai",edtRemainingCapacity.getText().toString().trim());
                }
                startActivity(intent);

            }
        });



    }


    private void setSpinner(){
        String[] firms = new String[]{};
        for(int i=0;i<arrayList.size();++i){
            firms[i]=arrayList.get(i).toString();
        }
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selecteditem =  spinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void check(){
        edtNameProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtNameProduct.getText().length()<= 0) {
                    tilNameProduct.setError("Không được để trống");
                    checkNameProduct=false;
                    button.setEnabled(false);
                }
                else {
                    tilNameProduct.setError(null);
                    checkNameProduct=true;
                    checkError();
                }
            }
        });
        edtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtPrice.getText().length()<= 0) {
                    tilPrice.setError("Không được để trống");
                    checkPrice=false;
                    button.setEnabled(false);
                }
                else {
                    tilPrice.setError(null);
                    checkPrice=true;
                    checkError();
                }
            }
        });
        edtDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtDescription.getText().length()<= 0) {
                    tilDescription.setError("Không được để trống");
                    checkDescription=false;
                    button.setEnabled(false);
                }
                else {
                    tilDescription.setError(null);
                    checkDescription=true;
                    checkError();
                }
            }
        });
        edtExpiryDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtExpiryDate.getText().length()<= 0) {
                    tilExpiryDate.setError("Không được để trống");
                    checkExpiryDate=false;
                    button.setEnabled(false);
                }
                else {
                    tilExpiryDate.setError(null);
                    checkExpiryDate=true;
                    checkError();
                }
            }
        });
        edtDateManufacture.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtDateManufacture.getText().length()<= 0) {
                    tilDateManufacture.setError("Không được để trống");
                    checkDateManufacture=false;
                    button.setEnabled(false);
                }
                else {
                    tilDateManufacture.setError(null);
                    checkDateManufacture=true;
                    checkError();
                }
            }
        });
        edtRemainingCapacity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (edtRemainingCapacity.getText().length()<= 0) {
                        tilRemainingCapacity.setError("Không được để trống");
                        checkRemainingCapacity=false;
                        button.setEnabled(false);
                    }
                    else {
                        tilRemainingCapacity.setError(null);
                        checkRemainingCapacity=true;
                        checkError();
                    }
                }
            });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              checkError();
            }
        });
    }

    private void checkError() {
        if(radioButtonPerfume.isChecked()){
            if(checkNameProduct==true&&checkExpiryDate==true&&checkDateManufacture==true&&checkPrice==true&&checkDescription==true&&checkRemainingCapacity==true){
                button.setEnabled(true);
            }else {
                button.setEnabled(false);
            }
        }else{
            if(checkNameProduct==true&&checkExpiryDate==true&&checkDateManufacture==true&&checkPrice==true&&checkDescription==true&&checkBox.isChecked()){
                button.setEnabled(true);
            }else {
                button.setEnabled(false);
            }
        }

    }

    public void eventImageButton(){
        imgBtnExpiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                final int year=c.get(Calendar.YEAR);
                final int month=c.get(Calendar.MONTH);
                final int day =c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog =new DatePickerDialog(DepositActivity.this
                        , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtExpiryDate.setText(day+"/"+month+"/"+year);
                    }
                }, year, month, day);

                dialog.show();
            }
        });

        imgBtnDateManufacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                final int year=c.get(Calendar.YEAR);
                final int month=c.get(Calendar.MONTH);
                final int day =c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog =new DatePickerDialog(DepositActivity.this
                        , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtDateManufacture.setText(day+"/"+month+"/"+year);
                    }
                }, year, month, day);

                dialog.show();
            }
        });
    }

    public void eventRadioButton(){
        radioButtonLipstick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButtonLipstick.isChecked()){
                    checkBox.setVisibility(View.VISIBLE);
                    edtRemainingCapacity.setVisibility(View.GONE);
                    tilRemainingCapacity.setVisibility(View.GONE);
                    checkError();
                }else{
                    checkBox.setVisibility(View.GONE);
                    edtRemainingCapacity.setVisibility(View.VISIBLE);
                    tilRemainingCapacity.setVisibility(View.VISIBLE);
                    checkError();

                }
            }
        });
        radioButtonPerfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButtonPerfume.isChecked()){
                    edtRemainingCapacity.setVisibility(View.VISIBLE);
                    tilRemainingCapacity.setVisibility(View.VISIBLE);
                    checkBox.setVisibility(View.GONE);
                    checkError();
                }else{
                    edtRemainingCapacity.setVisibility(View.GONE);
                    checkBox.setVisibility(View.VISIBLE);
                    tilRemainingCapacity.setVisibility(View.GONE);
                    checkError();
                }
            }
        });
    }

    private void loadFirm() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://mshop4girls.000webhostapp.com//getFirm.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String name = jsonObject.getString("ten");
                            arrayList.add(new Firm(id, name));
                            spinnerAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage()+"", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        super.onBackPressed();
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
}