package com.example.shop4girls.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4girls.R;
import com.example.shop4girls.connect.Server;
import com.example.shop4girls.model.OrderProduct;
import com.example.shop4girls.model.Product;
import com.example.shop4girls.view.DetailProductActivity;
import com.example.shop4girls.view.LoginActivity;
import com.example.shop4girls.view.SignInActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderProductAdapter extends RecyclerView.Adapter<OrderProductAdapter.ItemHolder> {
    public Context context;
    public ArrayList<OrderProduct> arrayList;
    private RatingBar ratingBar;

    public OrderProductAdapter(Context context, ArrayList<OrderProduct> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public OrderProductAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_order_product,null);
        OrderProductAdapter.ItemHolder itemHolder=new OrderProductAdapter.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderProductAdapter.ItemHolder itemHolder, final int i) {
        final OrderProduct product=arrayList.get(i);
        itemHolder.txtName.setText(product.getName());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        itemHolder.txtPrice.setText(decimalFormat.format(product.getPrice())+" đ");
        Picasso.get().load(product.getImage())
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_error)
                .into(itemHolder.image);
        if(product.getStatus()==1){
            itemHolder.button.setText("Xem lại");
            itemHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), DetailProductActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Product productView = new Product(product.getId(),product.getName(),product.getPrice(),product.getImage(),product.getDescription(),product.getCategory(),product.getRating());
                    intent.putExtra("thongtinsanpham", productView);
                    context.startActivity(intent);
                }
            });
        }else {
            itemHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AppCompatActivity activity=(AppCompatActivity)  v.getContext();
                    final Dialog dialogRating = new Dialog(activity);
                    //Gan content view cho dialog la mot layout tu dinh nghia
                    dialogRating.setContentView(R.layout.layout_custom_dialog_rating);

                    ratingBar = dialogRating.findViewById(R.id.ratingBar);
                    Button button = dialogRating.findViewById(R.id.button);
                    button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OrderProduct product=arrayList.get(i);
                        createRating(product.getId());
                        dialogRating.dismiss();
                    }
                });

                    // Setting wight and height dialog
                    dialogRating.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT);
                    dialogRating.show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView txtName,txtPrice;
        private Button button;

        public ItemHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageview);
            txtName=itemView.findViewById(R.id.textview_name);
            txtPrice=itemView.findViewById(R.id.textview_price);
            button = itemView.findViewById(R.id.button_order);
        }
    }

    private void createRating(final int idProduct){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.postRating, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")) {
                    Toast.makeText(context, "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Đánh giá không thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Lỗi Xảy Ra: " +error.getMessage(),Toast.LENGTH_LONG).show();
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
}
