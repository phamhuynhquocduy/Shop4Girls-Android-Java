package com.example.shop4girls.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop4girls.R;
import com.example.shop4girls.model.Cart;
import com.example.shop4girls.view.CartActivity;
import com.example.shop4girls.view.MainActivity;
import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ItemHolder> {
    private Context context;
    private ArrayList<Cart> cartArrayList;

    public CartAdapter(Context context, ArrayList<Cart> cartArrayList) {
        this.context = context;
        this.cartArrayList = cartArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cart,null);
        CartAdapter.ItemHolder itemHolder=new CartAdapter.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.ItemHolder itemHolder, final int i) {

        Cart cart= cartArrayList.get(i);
        itemHolder.txtName.setText(cart.getName());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        itemHolder.txtPrice.setText("Giá : "+decimalFormat.format(cart.getPirce())+" đ");
        Picasso.get()
                .load(cart.getImage())
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_error)
                .into(itemHolder.imageView);
        itemHolder.btnValues.setText(cart.getCount()+"");
        final int sl= Integer.parseInt(itemHolder.btnValues.getText().toString());
        if (sl>=10) {
            itemHolder.btnPlus.setVisibility(View.INVISIBLE);

        }else if(sl<=1){
            itemHolder.btnMinus.setVisibility(View.INVISIBLE);

        }else {
            itemHolder.btnMinus.setVisibility(View.VISIBLE);
            itemHolder.btnPlus.setVisibility(View.VISIBLE);
        }

        itemHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newCount=Integer.parseInt(itemHolder.btnValues.getText().toString())+1;
                int currentCount=cartArrayList.get(i).getCount();
                long currentPrice=MainActivity.arrayListCart.get(i).getPirce();
                MainActivity.arrayListCart.get(i).setCount(newCount);
                long newPrice=(currentPrice*newCount)/currentCount;
                MainActivity.arrayListCart.get(i).setPirce(newPrice);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                CartActivity.eventTotal();
                if(newCount>9){
                    itemHolder.btnValues.setText(String.valueOf(newCount));
                    itemHolder.btnMinus.setVisibility(View.VISIBLE);
                    itemHolder.btnPlus.setVisibility(View.INVISIBLE);
                }else{
                    itemHolder.btnValues.setText(String.valueOf(newCount));
                    itemHolder.btnMinus.setVisibility(View.VISIBLE);
                    itemHolder.btnPlus.setVisibility(View.VISIBLE);
                }
            }
        });
        itemHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newCount =Integer.parseInt(itemHolder.btnValues.getText().toString())-1;
                int currentCount=MainActivity.arrayListCart.get(i).getCount();
                long currentPrice = MainActivity.arrayListCart.get(i).getPirce();
                MainActivity.arrayListCart.get(i).setCount(newCount);
                long newPrice=(currentPrice*newCount)/currentCount;
                MainActivity.arrayListCart.get(i).setPirce(newPrice);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                CartActivity.eventTotal();
                if(newCount<2){
                    itemHolder.btnValues.setText(String.valueOf(newCount));
                    itemHolder.btnMinus.setVisibility(View.INVISIBLE);
                    itemHolder.btnPlus.setVisibility(View.VISIBLE);
                }else{
                    itemHolder.btnValues.setText(String.valueOf(newCount));
                    itemHolder.btnMinus.setVisibility(View.VISIBLE);
                    itemHolder.btnPlus.setVisibility(View.VISIBLE);
                }
            }
        });
        itemHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartArrayList.remove(MainActivity.arrayListCart.get(i));
                notifyDataSetChanged();
                CartActivity.eventTotal();
                if(MainActivity.arrayListCart.size() <= 0){
                    CartActivity.txtNotification.setVisibility(View.VISIBLE);
                    CartActivity.recyclerView.setVisibility(View.INVISIBLE);
                }else{
                    CartActivity.txtNotification.setVisibility(View.INVISIBLE);
                    CartActivity.recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }
    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView txtName,txtPrice;
        public Button btnValues,btnMinus,btnPlus;
        public ImageButton imageButton;

        public ItemHolder(View itemView) {
            super(itemView);
           txtName=itemView.findViewById(R.id.textview_name);
           txtPrice=itemView.findViewById(R.id.textview_price);
           imageView=itemView.findViewById(R.id.imageview);
           btnValues=itemView.findViewById(R.id.button_values);
           btnMinus=itemView.findViewById(R.id.button_minus);
           btnPlus=itemView.findViewById(R.id.button_plus);
           imageButton=itemView.findViewById(R.id.img_btn_cart);
        }
    }
}
