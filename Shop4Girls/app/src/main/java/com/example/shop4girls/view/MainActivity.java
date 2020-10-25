package com.example.shop4girls.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4girls.R;
import com.example.shop4girls.adapter.CategoryHomeAdapter;
import com.example.shop4girls.adapter.EyeAdapter;
import com.example.shop4girls.adapter.FaceAdapter;
import com.example.shop4girls.adapter.FavoriteProductAdapter;
import com.example.shop4girls.adapter.LipAdapter;
import com.example.shop4girls.adapter.NewProductAdpater;
import com.example.shop4girls.connect.CheckConnection;
import com.example.shop4girls.connect.SaveSharedPreference;
import com.example.shop4girls.connect.Server;
import com.example.shop4girls.model.Cart;
import com.example.shop4girls.model.Category;
import com.example.shop4girls.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerViewNewProduct,recyclerViewCategory,recyclerViewEye,recyclerViewFace,recyclerViewLip;
    private NewProductAdpater newProductAdpater;
    private ArrayList<Product> arrayListNewProdcut,arrayListEye,arrayListFace, arrayListLip;
    private CategoryHomeAdapter categoryHomeAdapter;
    private ArrayList<Category> arrayListCategory;
    private EyeAdapter eyeAdapter;
    private FaceAdapter faceAdapter;
    private LipAdapter lipAdapter;
    public static ArrayList<Cart> arrayListCart;
    public static ArrayList<Product> arrayListFavorite= new ArrayList<>();
    public static FavoriteProductAdapter favoriteProductAdapter;

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
        //EyeCategory
        recyclerViewEye = findViewById(R.id.recyclerview_eye);
        arrayListEye = new ArrayList<>();
        eyeAdapter = new EyeAdapter(getApplicationContext(), arrayListEye);
        GridLayoutManager gridLayoutManagerEye = new GridLayoutManager(this, 1);
        gridLayoutManagerEye.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerViewEye.setLayoutManager(gridLayoutManagerEye);
        recyclerViewEye.setHasFixedSize(true);
        recyclerViewEye.setAdapter(eyeAdapter);
        //FaceCategory
        recyclerViewFace = findViewById(R.id.recyclerview_face);
        arrayListFace = new ArrayList<>();
        faceAdapter = new FaceAdapter(getApplicationContext(), arrayListFace);
        GridLayoutManager gridLayoutManagerFace = new GridLayoutManager(this, 1);
        gridLayoutManagerFace.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerViewFace.setLayoutManager(gridLayoutManagerFace);
        recyclerViewFace.setHasFixedSize(true);
        recyclerViewFace.setAdapter(faceAdapter);
        //LipCategory
        recyclerViewLip = findViewById(R.id.recyclerview_lip);
        arrayListLip = new ArrayList<>();
        lipAdapter = new LipAdapter(getApplicationContext(), arrayListLip);
        GridLayoutManager gridLayoutManagerLip = new GridLayoutManager(this, 1);
        gridLayoutManagerLip.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerViewLip.setLayoutManager(gridLayoutManagerLip);
        recyclerViewLip.setHasFixedSize(true);
        recyclerViewLip.setAdapter(lipAdapter);

        setActionBar();
        setUpNavDrawer();
        setViewFlipper();
        getNewProduct();
        getCategory();
        getFavorite();
        getTokenFMC();
        loadProduct(5,arrayListEye);
        loadProduct(6,arrayListFace);
        loadProduct(7,arrayListLip);
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
                    case R.id.nav_my_account: {
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
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, OrderProductActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_short(getApplicationContext(), "Lỗi");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                    case R.id.nav_my_wishlist: {
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_short(getApplicationContext(), "Lỗi");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                    case R.id.nav_support: {
                        return true;
                    }
                    case R.id.nav_sign_out: {
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            SaveSharedPreference.setUserName(MainActivity.this,"");
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
        mangquangcao.add("https://iweb.tatthanh.com.vn/pic/3/blog/images/banner-my-pham(15).jpg");
        mangquangcao.add("https://hali.vn/wp-content/uploads/2020/07/thiet-ke-banner-my-pham3.jpg");
        mangquangcao.add("https://iweb.tatthanh.com.vn/pic/3/blog/images/banner-my-pham(7).jpg");
        mangquangcao.add("https://iweb.tatthanh.com.vn/pic/3/blog/images/banner-my-pham(9).jpg");
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

    public void getFavorite() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getFavorite, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        arrayListFavorite.add(new Product(jsonObject.getInt("id")
                                , jsonObject.getString("tensp")
                                , jsonObject.getInt("giasp")
                                , jsonObject.getString("hinhanhsp")
                                , jsonObject.getString("motasp")
                                , jsonObject.getInt("idsanpham")));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    CheckConnection.ShowToast_short(getApplicationContext(), e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idtaikhoan", String.valueOf(LoginActivity.id));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getTokenFMC(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.d("gettokenfail","Lỗi");
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.d("gettoken", token);
                    }
                });
        FirebaseMessaging.getInstance().subscribeToTopic("TopicName");
    }

    private void loadProduct(final int idCate, final ArrayList<Product> arrayList) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.getCategoryProduct+String.valueOf(idCate), new Response.Listener<JSONArray>() {
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
                            arrayList.add(new Product(id, name, price, image, description, idCategory));
                            if(idCate==5){
                                eyeAdapter.notifyDataSetChanged();
                            }else if(idCate==6){
                                faceAdapter.notifyDataSetChanged();
                            }else if(idCate==7){
                                lipAdapter.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage()+"", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}