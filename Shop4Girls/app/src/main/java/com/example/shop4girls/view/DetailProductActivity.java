package com.example.shop4girls.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
import com.example.shop4girls.adapter.CartAdapter;
import com.example.shop4girls.connect.CheckConnection;
import com.example.shop4girls.connect.Server;
import com.example.shop4girls.model.Cart;
import com.example.shop4girls.model.Product;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailProductActivity extends AppCompatActivity {
    private TextView txtName, txtPrice,txtDescription;
    private ImageView imageView;
    private Toolbar toolbar;
    private ImageButton btnFavorite;
    private Button btnCart, btnMinus, btnPlus , btnValues, btnDeposit;
    public int id=0;
    public String name="";
    public int price=0;
    public String image="";
    public String description="";
    public int idcategory=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);


        toolbar=findViewById(R.id.toolbar);
        txtPrice=findViewById(R.id.textview_price);
        txtDescription=findViewById(R.id.textview_description);
        txtName=findViewById(R.id.textview_name);
        imageView=findViewById(R.id.imageview);
        btnCart=  findViewById(R.id.button_cart);
        btnFavorite = findViewById(R.id.btn_favorite);
        btnMinus = findViewById(R.id.button_minus);
        btnPlus = findViewById(R.id.button_plus);
        btnValues = findViewById(R.id.button_values);
        getData();
        setActionBar();
        eventButtonCart();
        eventButtonFavorite();
        evenCountProduct();
    }


    private void evenCountProduct() {
        final int sl= Integer.parseInt(btnValues.getText().toString());
        if (sl>=10) {
            btnPlus.setVisibility(View.INVISIBLE);

        }else if(sl<=1){
            btnMinus.setVisibility(View.INVISIBLE);

        }else {
            btnMinus.setVisibility(View.VISIBLE);
            btnPlus.setVisibility(View.VISIBLE);
        }
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newCount =Integer.parseInt(btnValues.getText().toString())-1;
                if(newCount<2){
                    btnValues.setText(String.valueOf(newCount));
                    btnMinus.setVisibility(View.INVISIBLE);
                    btnPlus.setVisibility(View.VISIBLE);
                }else{
                    btnValues.setText(String.valueOf(newCount));
                    btnMinus.setVisibility(View.VISIBLE);
                    btnPlus.setVisibility(View.VISIBLE);
                }

            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newCount=Integer.parseInt(btnValues.getText().toString())+1;
                if(newCount>9){
                    btnValues.setText(String.valueOf(newCount));
                    btnMinus.setVisibility(View.VISIBLE);
                    btnPlus.setVisibility(View.INVISIBLE);
                }else{
                    btnValues.setText(String.valueOf(newCount));
                    btnMinus.setVisibility(View.VISIBLE);
                    btnPlus.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void eventButtonCart() {
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.arrayListCart.size()>0){
                    int count=Integer.parseInt(btnValues.getText().toString());
                    boolean exits=false;
                    for(int i=0;i<MainActivity.arrayListCart.size();i++){
                        if(MainActivity.arrayListCart.get(i).getId() == id){
                            MainActivity.arrayListCart.get(i).setCount(MainActivity.arrayListCart.get(i).getCount()+count);
                            if(MainActivity.arrayListCart.get(i).getCount()>10){
                                MainActivity.arrayListCart.get(i).setCount(10);
                            }
                            MainActivity.arrayListCart.get(i).setPirce(price*MainActivity.arrayListCart.get(i).getCount());
                            exits=true;
                        }
                    }
                    if(exits==false){
                        int newCount=Integer.parseInt(btnValues.getText().toString());
                        long newPrice =newCount*price;
                        MainActivity.arrayListCart.add(new Cart(id,name,newPrice,image,newCount));
                    }
                }
                else{
                    int newCount=Integer.parseInt(btnValues.getText().toString());
                    long newPrice=newCount*price;
                    MainActivity.arrayListCart.add(new Cart(id,name,newPrice,image,newCount));
                }
                Intent intent=new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        Product product= (Product) getIntent().getSerializableExtra("thongtinsanpham");
        id=product.getId();
        name=product.getName();
        price=product.getPrice();
        image=product.getImage();
        description=product.getDescription();
        idcategory=product.getIdCategory();
        int gia=product.getPrice();
        txtName.setText(product.getName());
        txtDescription.setText(product.getDescription());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtPrice.setText(decimalFormat.format(gia)+" đ");
        Picasso.get().load(product.getImage())
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_error)
                .into(imageView);
        btnFavorite.setTag(0);
        for(int i=0;i<MainActivity.arrayListFavorite.size();++i){
            if(product.getId()==MainActivity
                    .arrayListFavorite.get(i).getId()){
                btnFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_red_24);
                btnFavorite.setTag(1);
                break;
            }
        }
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

    private void eventButtonFavorite() {
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = (Integer) btnFavorite.getTag();
                if(flag==0){
                    final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.postFavorite, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("yeuthich",response);
                            if(response.equals("success")){
                                btnFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_red_24);
                                btnFavorite.setTag(1);
                                CheckConnection.ShowToast_short(getApplicationContext(),"Bạn đã thêm thành công");
                            }else{
                                CheckConnection.ShowToast_short(getApplicationContext(),"Dữ liệu của bạn đã bị lỗi"+response+String.valueOf(LoginActivity.id)+String.valueOf(id));
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap=new HashMap<String,String>();
                            hashMap.put("idtaikhoan",String.valueOf(LoginActivity.id));
                            hashMap.put("idsanpham",String.valueOf(id));
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else{
                    final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.delFavorite, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("tagconverxoa", "["+response+"]");
                            if(response.equals("success")){
                                btnFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_border_red__24);
                                btnFavorite.setTag(0);
                                CheckConnection.ShowToast_short(getApplicationContext(),"Bạn đã xoá sản phẩm thành công");
                            }else{
                                CheckConnection.ShowToast_short(getApplicationContext(),"Dữ liệu của bạn đã bị lỗi");
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap=new HashMap<String,String>();
                            hashMap.put("idtaikhoan",String.valueOf(LoginActivity.id));
                            hashMap.put("idsanpham",String.valueOf(id));
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cart :
                Intent intent=new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}