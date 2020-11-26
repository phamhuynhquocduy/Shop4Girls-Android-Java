package com.example.shop4girls.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop4girls.R;
import com.example.shop4girls.model.Product;
import com.example.shop4girls.view.CartActivity;
import com.example.shop4girls.view.DetailProductActivity;
import com.example.shop4girls.view.FavoriteActivity;
import com.example.shop4girls.view.MainActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FavoriteProductAdapter extends RecyclerView.Adapter<FavoriteProductAdapter.ItemHolder> {
    public Context context;
    public ArrayList<Product> arrayList;

    public FavoriteProductAdapter(Context context, ArrayList<Product> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public FavoriteProductAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_favorite,null);
        FavoriteProductAdapter.ItemHolder itemHolder=new FavoriteProductAdapter.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteProductAdapter.ItemHolder itemHolder, final int i) {
        Product product=arrayList.get(i);
        itemHolder.txtName.setText(product.getName());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        itemHolder.txtPrice.setText(decimalFormat.format(product.getPrice())+" đ");
        Picasso.get().load(product.getImage())
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_error)
                .into(itemHolder.image);
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context.getApplicationContext(), DetailProductActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("thongtinsanpham", arrayList.get(i));
//                context.startActivity(intent);

            }
        });
        itemHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(MainActivity.arrayListFavorite.get(i));
                notifyDataSetChanged();
                if(MainActivity.arrayListFavorite.size() <= 0){
                    FavoriteActivity.txtProductEmpty.setVisibility(View.VISIBLE);
                    FavoriteActivity.recyclerView.setVisibility(View.INVISIBLE);
                }else{
                    FavoriteActivity.txtProductEmpty.setVisibility(View.INVISIBLE);
                    FavoriteActivity.recyclerView.setVisibility(View.VISIBLE);
                }
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
        public ImageButton imageButton;

        public ItemHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageview);
            txtName=itemView.findViewById(R.id.textview_name);
            txtPrice=itemView.findViewById(R.id.textview_price);
            imageButton =itemView.findViewById(R.id.img_btn);
        }
    }
}
