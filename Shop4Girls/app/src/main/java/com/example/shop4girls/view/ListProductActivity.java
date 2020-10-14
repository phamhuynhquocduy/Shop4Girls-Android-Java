package com.example.shop4girls.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4girls.R;
import com.example.shop4girls.adapter.ListProductAdapter;
import com.example.shop4girls.connect.Server;
import com.example.shop4girls.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListProductActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListProduct;
    private ArrayList arrayListListProduct;
    private ListProductAdapter listProductAdapter;
    private  int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        // Product
        recyclerViewListProduct = findViewById(R.id.recyclerview_list_product);
        arrayListListProduct = new ArrayList<>();
        listProductAdapter = new ListProductAdapter(getApplicationContext(), arrayListListProduct);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerViewListProduct.setLayoutManager(gridLayoutManager);
        recyclerViewListProduct.setAdapter(listProductAdapter);
        Intent intent = getIntent();
        id=intent.getIntExtra("idcategory",1);

        loadProduct();
    }
    private void loadProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.getProduct+id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String name = jsonObject.getString("tensp");
                            int price = jsonObject.getInt("giasp");
                            String image = jsonObject.getString("hinhanhsp");
                            String description = jsonObject.getString("motasp");
                            int idCategory = jsonObject.getInt("idsanpham");
                            arrayListListProduct.add(new Product(id, name, price, image, description, idCategory));
                            listProductAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Toast.makeText(ListProductActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListProductActivity.this, error.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}