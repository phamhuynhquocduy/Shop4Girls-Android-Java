package com.example.shop4girls.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop4girls.R;
import com.example.shop4girls.model.Category;
import com.example.shop4girls.view.ListProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryHomeAdapter  extends  RecyclerView.Adapter<CategoryHomeAdapter.ItemHolder>{
    public Context context;
    public ArrayList<Category> arrayList;

    public CategoryHomeAdapter(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CategoryHomeAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category,null);
        CategoryHomeAdapter.ItemHolder itemHolder=new CategoryHomeAdapter.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryHomeAdapter.ItemHolder itemHolder, final  int i) {
        final Category category=arrayList.get(i);
        itemHolder.txtName.setText(category.getName());
        Picasso.get().load(category.getImage())
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_error)
                .into(itemHolder.image);
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), ListProductActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("idcategory",category.getId());
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
        public TextView txtName;

        public ItemHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageview);
            txtName=itemView.findViewById(R.id.textview_name);
        }
    }

   }
