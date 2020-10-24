package com.example.shop4girls.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
import com.example.shop4girls.adapter.FavoriteProductAdapter;
import com.example.shop4girls.connect.CheckConnection;
import com.example.shop4girls.connect.Server;
import com.example.shop4girls.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView txtProductEmpty;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.recyclerview_favorite_product);
        toolbar = findViewById(R.id.toolbar);
        txtProductEmpty = findViewById(R.id.txt_product_empty);
        txtProductEmpty.setVisibility(View.INVISIBLE);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
            setActionBar();
            getData();

        }else{
            CheckConnection.ShowToast_short(getApplicationContext(),"Lỗi kết nối mạng");
        }
        //OrderProduct
        MainActivity.favoriteProductAdapter = new FavoriteProductAdapter(getApplicationContext(), MainActivity.arrayListFavorite);
        GridLayoutManager gridLayoutManagerFavorite = new GridLayoutManager(this, 1);
        gridLayoutManagerFavorite.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManagerFavorite);
        recyclerView.setAdapter(MainActivity.favoriteProductAdapter);
    }

    private void getData() {
        if(MainActivity.arrayListFavorite!=null){
            MainActivity.arrayListFavorite.clear();
        }
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.getFavorite, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            MainActivity.arrayListFavorite.add(new Product(jsonObject.getInt("id")
                                    ,jsonObject.getString("tensp")
                                    ,jsonObject.getInt("giasp")
                                    ,jsonObject.getString("hinhanhsp")
                                    ,jsonObject.getString("motasp")
                                    ,jsonObject.getInt("idsanpham")));
                        }
                        MainActivity.favoriteProductAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CheckConnection.ShowToast_short(getApplicationContext(),e.getMessage());
                    }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String, String>();
                param.put("idtaikhoan",String.valueOf(LoginActivity.id));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void setActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
