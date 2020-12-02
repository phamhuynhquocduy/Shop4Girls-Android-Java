package com.example.shop4girls.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.shop4girls.R;
import com.example.shop4girls.connect.DrawableClickListener;
import com.example.shop4girls.connect.CustomEditText;
import com.example.shop4girls.connect.ViewPrintAdapter;
import com.example.shop4girls.connect.DrawableClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class DepositActivity extends AppCompatActivity {

    private EditText edtCapacity , edtRemainingCapacity, edtNameProduct,edtExpiryDate,edtDateManufacture,edtPrice, edtDescription;
    private CheckBox checkBox;
    private RadioButton radioButtonLipstick, radioButtonPerfume;
    private TextInputLayout tilCapacity, tilRemainingCapacity;
    private Button button;
    private Context context;
    private ImageButton imgBtnExpiryDate, imgBtnDateManufacture;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        edtCapacity=findViewById(R.id.edt_capacity);
        edtRemainingCapacity=findViewById(R.id.edt_remaining_capacity);
        checkBox=findViewById(R.id.checkBox);
        radioButtonLipstick=findViewById(R.id.radiobutton_lipstick);
        radioButtonPerfume=findViewById(R.id.radiobutton_perfume);
        tilCapacity = findViewById(R.id.til_capacity);
        tilRemainingCapacity = findViewById(R.id.til_remaining_capacity);
        button = findViewById(R.id.button_print);
        edtNameProduct = findViewById(R.id.edt_name_prodcut);
        edtExpiryDate = findViewById(R.id.edt_expiry_date);
        edtDateManufacture = findViewById(R.id.edt_date_manufacture);
        imgBtnDateManufacture = findViewById(R.id.date_manufacture);
        imgBtnExpiryDate = findViewById(R.id.expiry_date);
        edtDescription = findViewById(R.id.edt_description);
        edtPrice = findViewById(R.id.edt_price);


        eventImageButton();
        eventRadioButton();


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
                if(radioButtonLipstick.isChecked()){
                    intent.putExtra("Type","Son");
                }else{
                    intent.putExtra("Type","Nước Hoa");
                }
                startActivity(intent);

            }
        });



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
                    edtCapacity.setVisibility(View.GONE);
                    edtRemainingCapacity.setVisibility(View.GONE);
                    tilCapacity.setVisibility(View.GONE);
                    tilRemainingCapacity.setVisibility(View.GONE);
                }else{
                    checkBox.setVisibility(View.GONE);
                    edtCapacity.setVisibility(View.VISIBLE);
                    edtRemainingCapacity.setVisibility(View.VISIBLE);
                    tilCapacity.setVisibility(View.VISIBLE);
                    tilRemainingCapacity.setVisibility(View.VISIBLE);
                }
            }
        });
        radioButtonPerfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButtonPerfume.isChecked()){
                    edtCapacity.setVisibility(View.VISIBLE);
                    edtRemainingCapacity.setVisibility(View.VISIBLE);
                    tilCapacity.setVisibility(View.VISIBLE);
                    tilRemainingCapacity.setVisibility(View.VISIBLE);
                    checkBox.setVisibility(View.GONE);
                }else{
                    edtCapacity.setVisibility(View.GONE);
                    edtRemainingCapacity.setVisibility(View.GONE);
                    checkBox.setVisibility(View.VISIBLE);
                    tilCapacity.setVisibility(View.GONE);
                    tilRemainingCapacity.setVisibility(View.GONE);
                }
            }
        });
    }
}