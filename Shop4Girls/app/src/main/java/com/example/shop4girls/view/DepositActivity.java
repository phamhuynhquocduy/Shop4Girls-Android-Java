package com.example.shop4girls.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.shop4girls.R;
import com.example.shop4girls.connect.ViewPrintAdapter;
import com.google.android.material.textfield.TextInputLayout;

public class DepositActivity extends AppCompatActivity {

    private EditText edtCapacity , edtRemainingCapacity;
    private CheckBox checkBox;
    private RadioButton radioButtonLipstick, radioButtonPerfume;
    private TextInputLayout tilCapacity, tilRemainingCapacity;
    private Button button;

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

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                PrintManager printManager = (PrintManager) getSystemService(PRINT_SERVICE);
                printManager.print("print_any_view_job_name", new ViewPrintAdapter(getApplicationContext(),
                        findViewById(R.id.linearLayout)), null);
            }
        });


    }
}