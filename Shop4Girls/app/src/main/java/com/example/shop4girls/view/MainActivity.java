package com.example.shop4girls.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4girls.R;
import com.example.shop4girls.adapter.CategoryHomeAdapter;
import com.example.shop4girls.adapter.NewProductAdpater;
import com.example.shop4girls.connect.CheckConnection;
import com.example.shop4girls.connect.Server;
import com.example.shop4girls.model.Cart;
import com.example.shop4girls.model.Category;
import com.example.shop4girls.model.Product;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerViewNewProduct,recyclerViewCategory;
    private NewProductAdpater newProductAdpater;
    private ArrayList<Product> arrayListNewProdcut;
    private CategoryHomeAdapter categoryHomeAdapter;
    private ArrayList<Category> arrayListCategory;
    public static ArrayList<Cart> arrayListCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toobar_main);
        viewFlipper = findViewById(R.id.viewlipper);
        //Cart
        if (arrayListCart != null) {
        } else {
            arrayListCart = new ArrayList<>();
        }
        //New Product
        recyclerViewNewProduct = findViewById(R.id.recyclerview_new_product);
        arrayListNewProdcut = new ArrayList<>();
        newProductAdpater = new NewProductAdpater(getApplicationContext(), arrayListNewProdcut);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerViewNewProduct.setLayoutManager(gridLayoutManager);
        recyclerViewNewProduct.setAdapter(newProductAdpater);
        //Category
        recyclerViewCategory = findViewById(R.id.recyclerview_category);
        arrayListCategory = new ArrayList<>();
        categoryHomeAdapter = new CategoryHomeAdapter(getApplicationContext(), arrayListCategory);
        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(this, 1);
        gridLayoutManagerCategory.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerViewCategory.setLayoutManager(gridLayoutManagerCategory);
        recyclerViewCategory.setAdapter(categoryHomeAdapter);

        setActionBar();
        setUpNavDrawer();
        setViewFlipper();
        getNewProduct();
        getCategory();

    }

    private void getNewProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.getNewProduct, new Response.Listener<JSONArray>() {
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
                            arrayListNewProdcut.add(new Product(id, name, price, image, description, idCategory));
                            newProductAdpater.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show()
                            ;e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void setUpNavDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                final int menuItemId = menuItem.getItemId();
                switch (menuItemId) {
                    case R.id.nav_my_account:{
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_short(getApplicationContext(), "Lỗi");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    case R.id.nav_my_orders: {
                        return true;
                    }
                    case R.id.nav_my_rewrad: {
                        return true;
                    }
                    case R.id.nav_my_wishlist: {
                        return true;
                    }
                    case R.id.nav_support:{
                        return true;
                    }
                    case R.id.nav_sign_out:{
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            CheckConnection.ShowToast_short(getApplicationContext(), "Lỗi");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }
        });

    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void setViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cdn.tgdd.vn/Files/2020/04/29/1252555/laptop_800x450.jpg");
        mangquangcao.add("https://cdn.tgdd.vn/Files/2020/04/28/1252375/laptople_800x450.jpg");
        mangquangcao.add("https://cdn.tgdd.vn/Files/2020/04/23/1251226/1200-628_800x450.png");
        mangquangcao.add("https://cdn.tgdd.vn/Files/2020/05/04/1253433/fb_800x449.png");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
            viewFlipper.setAutoStart(true);
            Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
            Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
            viewFlipper.setInAnimation(animation_slide_in);
            viewFlipper.setOutAnimation(animation_slide_out);
        }
    }

    private void getCategory() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.getCategory, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String name = jsonObject.getString("ten");
                            String image = jsonObject.getString("hinh");
                            int idParent = jsonObject.getInt("idcha");
                            arrayListCategory.add(new Category(id, name, image, idParent));
                            categoryHomeAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}