package com.example.shop4girls.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop4girls.R;
import com.example.shop4girls.connect.SaveSharedPreference;
import com.example.shop4girls.connect.Server;
import com.example.shop4girls.model.Product;
import com.example.shop4girls.view.DetailProductActivity;
import com.example.shop4girls.view.LoginActivity;
import com.example.shop4girls.view.MainActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ListProductAdapter extends  RecyclerView.Adapter<ListProductAdapter.ItemHolder> {
    public Context context;
    public ArrayList<Product> arrayList;
    float sum=0;
    public static ArrayList<Product> arrayListCopy = new ArrayList<Product>();

    public ListProductAdapter(Context context, ArrayList<Product> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ListProductAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_product,null);
        ListProductAdapter.ItemHolder itemHolder=new ListProductAdapter.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductAdapter.ItemHolder itemHolder, final int i) {
        Product product=arrayList.get(i);
        itemHolder.txtName.setText(product.getName());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        itemHolder.txtPrice.setText(decimalFormat.format(product.getPrice())+" đ");
        itemHolder.ratingBar.setRating((float) product.getRating());
        itemHolder.ratingBar.setStepSize((float) product.getRating());
        Picasso.get().load(product.getImage())
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_error)
                .into(itemHolder.image);
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), DetailProductActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("thongtinsanpham", arrayList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView txtName,txtPrice;
        public RatingBar ratingBar;

        public ItemHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageview);
            txtName=itemView.findViewById(R.id.textview_name);
            txtPrice=itemView.findViewById(R.id.textview_price);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

    public void  filterName(String charText){
        charText=charText.toLowerCase(Locale.getDefault());
        arrayList.clear();
        if(charText.length() == 0){
            arrayList.addAll(arrayListCopy);
        }else{
            for(Product product : arrayListCopy ){
                if(product.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    arrayList .add(product);
                }
            }
        }
        notifyDataSetChanged();
    }
}
