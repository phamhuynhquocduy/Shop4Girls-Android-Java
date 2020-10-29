package com.example.shop4girls.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop4girls.R;
import com.example.shop4girls.adapter.CartAdapter;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {
    public static RecyclerView recyclerView;
    public static TextView txtNotification;
    public static TextView txtTotal;
    private static long tongtien=0;
    private Button btnPayment,btnContinue;
    private Toolbar toolbar;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerview_cart);
        txtTotal=findViewById(R.id.textview_price);
        txtNotification=findViewById(R.id.textview_notification);
        btnPayment=findViewById(R.id.button_payment);
        btnContinue=findViewById(R.id.button_continue);
        toolbar=findViewById(R.id.toolbar);
        adapter=new CartAdapter(getApplicationContext(),MainActivity.arrayListCart);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        eventButtonContinue();
        eventTotal();
        setActionBar();
        checkData();
        eventButtonPayment();
    }

    private void eventButtonPayment() {
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InformationOrderActivity.class));
            }
        });
    }

    private void eventButtonContinue() {
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }

    public static void eventTotal() {
        tongtien=0;
        for(int i=0;i<MainActivity.arrayListCart.size();i++){
            tongtien+=MainActivity.arrayListCart.get(i).getPirce();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotal.setText(decimalFormat.format(tongtien)+" đ");
    }

    private void setActionBar() {
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

    private void checkData() {
        if(MainActivity.arrayListCart.size() <= 0){
            adapter.notifyDataSetChanged();
            txtNotification.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }else{
            adapter.notifyDataSetChanged();
            txtNotification.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}