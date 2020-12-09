package com.example.shop4girls.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shop4girls.R;
import com.example.shop4girls.connect.ViewPrintAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BillDepositActivity extends AppCompatActivity {
    public static Context context;
    public static View view;
    private Toolbar toolbar;
    private TextView txtName, txtPhone,txtEmail,txtAddress,txtNameProduct,txtCurrentDdate,txtExpiryDate,txtDateManufacture,txtPrice, txtDescription,txtType,txtStatus,txtSend,txtFirm;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_deposit);
        context=getApplicationContext();
        toolbar = findViewById(R.id.toolbar);
        txtName = findViewById(R.id.txt_name);
        txtPhone = findViewById(R.id.txt_phone);
        txtAddress = findViewById(R.id.txt_address);
        txtEmail = findViewById(R.id.txt_email);
        txtNameProduct = findViewById(R.id.txt_name_product);
        txtCurrentDdate = findViewById(R.id.txt_current_date);
        txtExpiryDate = findViewById(R.id.txt_expiry_date);
        txtDateManufacture = findViewById(R.id.txt_date_manufacture);
        txtPrice = findViewById(R.id.txt_price);
        txtDescription = findViewById(R.id.txt_description);
        txtType = findViewById(R.id.txt_type_prodcut);
        txtStatus = findViewById(R.id.txt_status);
        txtSend = findViewById(R.id.txt_send);
        txtFirm =findViewById(R.id.txt_maker_product);

        setInforBill();
        //Print
        PrintManager printManager = (PrintManager) getSystemService(PRINT_SERVICE);
        printManager.print("print_any_view_job_name", new ViewPrintAdapter(getcontext(),
                findViewById(R.id.linearLayout3)), null);
    }

    public static Context getcontext(){
        return context;
    }

    private  void setInforBill(){
        txtEmail.setText(MainActivity.account.getEmail());
        txtAddress.setText(MainActivity.account.getAddress());
        txtPhone.setText(MainActivity.account.getPhone());
        txtName.setText(MainActivity.account.getLastName()+" "+MainActivity.account.getFirstName());
        txtNameProduct.setText(getIntent().getStringExtra("name"));
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        txtCurrentDdate.setText(timeStamp);
        txtPrice.setText(getIntent().getStringExtra("Price"));
        txtDateManufacture.setText(getIntent().getStringExtra("DateManufacture"));
        txtExpiryDate.setText(getIntent().getStringExtra("ExpiryDate"));
        txtDescription.setText(getIntent().getStringExtra("Description"));
        txtType.setText(getIntent().getStringExtra("Type"));
        txtStatus.setText(getIntent().getStringExtra("TrangThai"));
        txtSend.setText(MainActivity.account.getLastName()+" "+MainActivity.account.getFirstName());
        txtFirm.setText(getIntent().getStringExtra("Firm"));
    }

}
