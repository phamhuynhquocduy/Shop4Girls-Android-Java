package com.example.shop4girls.view;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4girls.R;
import com.example.shop4girls.adapter.OrderProductAdapter;
import com.example.shop4girls.connect.CheckConnection;
import com.example.shop4girls.connect.RecyclerItemClickListener;
import com.example.shop4girls.connect.Server;
import com.example.shop4girls.model.OrderProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderProductActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView txtProductEmpty;
    private Toolbar toolbar;
    private ArrayList<OrderProduct> arrayList;
    private OrderProductAdapter orderProductAdapter;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_product);

        recyclerView = findViewById(R.id.recyclerview_order_product);
        toolbar = findViewById(R.id.toolbar);
        txtProductEmpty = findViewById(R.id.txt_product_empty);
        txtProductEmpty.setVisibility(View.INVISIBLE);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
            setActionBar();
            getData();
            eventClickItem();

        }else{
            CheckConnection.ShowToast_short(getApplicationContext(),"Lỗi kết nối mạng");
        }
        //OrderProduct
        arrayList = new ArrayList<>();
        orderProductAdapter = new OrderProductAdapter(getApplicationContext(), arrayList);
        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(this, 1);
        gridLayoutManagerCategory.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManagerCategory);
        recyclerView.setAdapter(orderProductAdapter);
    }

    private void eventClickItem() {
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final AppCompatActivity activity=(AppCompatActivity)view.getContext();
                final Dialog dialogRating = new Dialog(activity);
                //Gan content view cho dialog la mot layout tu dinh nghia
                dialogRating.setContentView(R.layout.layout_custom_dialog_rating);

                ratingBar = dialogRating.findViewById(R.id.ratingBar);
                Button button = dialogRating.findViewById(R.id.button);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OrderProduct product=arrayList.get(position);
                        createRating(product.getId());

                    }
                });

                // Setting wight and height dialog
                dialogRating.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                dialogRating.show();
            }
        }));
    }

    private void getData(){
        final DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.getOrderProduct, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null && response.length() != 2){
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject object=jsonArray.getJSONObject(i);
                            arrayList.add(new OrderProduct(
                                    object.getInt("idsp"),
                                    object.getString("tensp"),
                                    object.getString("hinhanhsp"),
                                    object.getInt("giasp"),
                                    object.getInt("soluongsanpham"),
                                    object.getInt("tientungsanpham")));
                            orderProductAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(OrderProductActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    txtProductEmpty.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("idkhachhang",String.valueOf(LoginActivity.id));
                return map;
            }
        };
        requestQueue.add(stringRequest);
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

    private void createRating(final int idProduct){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.postRating, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")) {
                    Toast.makeText(getApplicationContext(), "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Đánh giá không thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Lỗi Xảy Ra: " +error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idsanpham", String.valueOf(idProduct));
                param.put("sodanhgia", String.valueOf(ratingBar.getRating()));
                param.put("idkhachhang", String.valueOf(LoginActivity.id));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
//    public float getSum(final int loai){
//        RequestQueue requestQueue2 = Volley.newRequestQueue(context);
//        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, Server.sumRating, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                sum = Float.parseFloat(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context,"Lỗi Xảy Ra: " +error.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> param = new HashMap<String, String>();
//                param.put("id", String.valueOf(loai));
//                return param;
//            }
//        };
//        requestQueue2.add(stringRequest2);
//        return sum;
//    }
}