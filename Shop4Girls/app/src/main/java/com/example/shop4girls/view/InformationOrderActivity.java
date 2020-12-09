package com.example.shop4girls.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4girls.R;
import com.example.shop4girls.connect.CheckConnection;
import com.example.shop4girls.connect.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InformationOrderActivity extends AppCompatActivity {
    private Button btnOrder;
    private Toolbar toolbar;
    private TextView txtName, txtPhone ,txtAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_order);
        btnOrder = findViewById(R.id.button_order);
        toolbar = findViewById(R.id.toolbar);
        txtName = findViewById(R.id.txtName_Order);
        txtPhone = findViewById(R.id.txtPhone_Order);
        txtAddress = findViewById(R.id.txtAddrerss_Order);
        eventOrder();
    }

    private void eventOrder() {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.getOder, new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String madonhang) {
                        Log.d("mahoadon",madonhang);
                        if(Integer.parseInt(madonhang)>0){
                            RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
                            StringRequest  stringRequest1=new StringRequest(Request.Method.POST, Server.getDetailOder, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("machitiec",response);
                                    if(response.equals("success")){
                                        MainActivity.arrayListCart.clear();
                                        CheckConnection.ShowToast_short(getApplicationContext(),"Bạn đã thêm giỏ hàng thành công");
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        CheckConnection.ShowToast_short(getApplicationContext(),"Mời bạn tiếp tục mua sản phẩm");
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
                                    JSONArray jsonArray=new JSONArray();

                                    for(int i=0;i<MainActivity.arrayListCart.size();i++){
                                        JSONObject object=new JSONObject();
                                        try {
                                            object.put("madonhang",madonhang);
                                            object.put("masanpham",MainActivity.arrayListCart.get(i).getId());
                                            object.put("soluongsanpham",MainActivity.arrayListCart.get(i).getCount());
                                            object.put("tientungsanpham",MainActivity.arrayListCart.get(i).getPirce());

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        jsonArray.put(object);

                                    }
                                    HashMap<String,String> hashMap=new HashMap<String, String>();
                                    hashMap.put("json",jsonArray.toString());
                                    return hashMap;
                                }
                            };
                            queue.add(stringRequest1);
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
                        hashMap.put("idkhachhang",String.valueOf(LoginActivity.id));
                        hashMap.put("tongtien",String.valueOf(CartActivity.total));
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);


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
    private void setInfoOrder(){
        txtAddress.setText(MainActivity.account.getAddress());
        txtPhone.setText(MainActivity.account.getPhone());
        txtName.setText(MainActivity.account.getLastName()+" "+MainActivity.account.getFirstName());
    }

}