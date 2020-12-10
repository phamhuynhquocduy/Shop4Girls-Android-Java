package com.example.shop4girls.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
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

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class InformationOrderActivity extends AppCompatActivity {
    private Button btnOrder;
    private Toolbar toolbar;
    private TextView txtName, txtPhone , txtAddress, txtTotal, txtTransport, txtTotalBill;
    private RadioButton radioButtonGHThuong, radioButtonGHNhanh, radioButtonGHNhanhTrongNgay, radioButtonGHTrong3h;
    private long total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_order);
        btnOrder = findViewById(R.id.button_order);
        toolbar = findViewById(R.id.toolbar);
        txtName = findViewById(R.id.txtName_Order);
        txtPhone = findViewById(R.id.txtPhone_Order);
        txtAddress = findViewById(R.id.txtAddrerss_Order);
        txtTotal = findViewById(R.id.txt_total);
        txtTransport = findViewById(R.id.txtTienVanChuyen);
        txtTotalBill = findViewById(R.id.txtToTalBill);
        radioButtonGHThuong = findViewById(R.id.radiobutton_GHThuong);
        radioButtonGHNhanh = findViewById(R.id.radiobutton_GHNhanh);
        radioButtonGHNhanhTrongNgay = findViewById(R.id.radiobutton_GHNhanhTrongNgay);
        radioButtonGHTrong3h = findViewById(R.id.radiobutton_GHTrong3h);
        eventOrder();
        setActionBar();
        setInfoOrder();
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
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        total= getIntent().getLongExtra("tongtien",0);
        txtTotal.setText(decimalFormat.format(total)+" đ");
        txtTransport.setText("25.000 đ");
        DecimalFormat decimalFormat1 = new DecimalFormat("###,###,###");
        txtTotalBill.setText(decimalFormat1.format( total+25000)+" đ");

        radioButtonGHThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        radioButtonGHTrong3h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtTotalBill.setText(decimalFormat.format( total+69000)+" đ");
                txtTransport.setText("69.000 đ");
            }
        });
        radioButtonGHNhanhTrongNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtTotalBill.setText(decimalFormat.format( total+45000)+" đ");
                txtTransport.setText("45.000 đ");
            }
        });
        radioButtonGHNhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtTotalBill.setText(decimalFormat.format( total+35000)+" đ");
                txtTransport.setText("35.000 đ");
            }
        });
    }
}