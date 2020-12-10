package com.example.shop4girls.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop4girls.R;
import com.example.shop4girls.model.Product;
import com.example.shop4girls.view.DetailProductActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class EyeAdapter extends RecyclerView.Adapter<EyeAdapter.ItemHolder> {
    public Context context;
    public ArrayList<Product> arrayList;

    public EyeAdapter(Context context, ArrayList<Product> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public EyeAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_new_category_product,null);
        EyeAdapter.ItemHolder itemHolder=new EyeAdapter.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EyeAdapter.ItemHolder itemHolder, final int i) {
        Product product=arrayList.get(i);
        itemHolder.txtName.setText(product.getName());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        itemHolder.txtPrice.setText(decimalFormat.format(product.getPrice())+" đ");
        itemHolder.ratingBar.setRating((float) product.getRating());
        Picasso.get().load(product.getImage())
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_error)
                .into(itemHolder.image);
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), DetailProductActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
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
}
