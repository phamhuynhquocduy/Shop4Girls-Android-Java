package com.example.shop4girls.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop4girls.R;
import com.example.shop4girls.model.Cart;
import com.example.shop4girls.view.CartActivity;
import com.example.shop4girls.view.MainActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Cart> cartArrayList;

    public CartAdapter(Context context, ArrayList<Cart> manggiohang) {
        this.context = context;
        this.cartArrayList = manggiohang;
    }

    @Override
    public int getCount() {
        return cartArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class  ViewHolder{
        TextView txtName;
        static TextView txtPrice;
        ImageView imageView;
        static Button btnPlus;
        static Button btnValues;
        static Button btnMinus;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(viewHolder==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.layout_cart,null);
            viewHolder.txtName=convertView.findViewById(R.id.textview_name);
            viewHolder.txtPrice=convertView.findViewById(R.id.textview_price);
            viewHolder.imageView=convertView.findViewById(R.id.imageview);
            viewHolder.btnValues=convertView.findViewById(R.id.button_values);
            viewHolder.btnMinus=convertView.findViewById(R.id.button_minus);
            viewHolder.btnPlus=convertView.findViewById(R.id.button_plus);

        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Cart cart= (Cart) getItem(position);
        viewHolder.txtName.setText(cart.getName());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText("Giá : "+decimalFormat.format(cart.getPirce())+" đ");
        Picasso.get()
                .load(cart.getImage())
                .placeholder(R.drawable.image_place_holder)
                .error(R.drawable.image_error)
                .into(viewHolder.imageView);
        viewHolder.btnValues.setText(cart.getCount()+"");
        final int sl= Integer.parseInt(viewHolder.btnValues.getText().toString());
        if (sl>=10) {
            viewHolder.btnPlus.setVisibility(View.INVISIBLE);

        }else if(sl<=1){
            viewHolder.btnMinus.setVisibility(View.INVISIBLE);

        }else {
            viewHolder.btnMinus.setVisibility(View.VISIBLE);
            viewHolder.btnPlus.setVisibility(View.VISIBLE);
        }

        viewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newCount=Integer.parseInt(ViewHolder.btnValues.getText().toString())+1;
                int currentCount=MainActivity.arrayListCart.get(position).getCount();
                long currentPrice=MainActivity.arrayListCart.get(position).getPirce();
                MainActivity.arrayListCart.get(position).setCount(newCount);
                long newPrice=(currentPrice*newCount)/currentCount;
                MainActivity.arrayListCart.get(position).setPirce(newPrice);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                ViewHolder.txtPrice.setText("Gía : "+decimalFormat.format(newPrice)+" đ");
                CartActivity.eventTotal();
                if(newCount>9){
                    ViewHolder.btnValues.setText(String.valueOf(newCount));
                    ViewHolder.btnMinus.setVisibility(View.VISIBLE);
                    ViewHolder.btnPlus.setVisibility(View.INVISIBLE);
                }else{
                    ViewHolder.btnValues.setText(String.valueOf(newCount));
                    ViewHolder.btnMinus.setVisibility(View.VISIBLE);
                    ViewHolder.btnPlus.setVisibility(View.VISIBLE);
                }
            }
        });
        ViewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newCount =Integer.parseInt(ViewHolder.btnValues.getText().toString())-1;
                int currentCount=MainActivity.arrayListCart.get(position).getCount();
                long currentPrice = MainActivity.arrayListCart.get(position).getPirce();
                MainActivity.arrayListCart.get(position).setCount(newCount);
                long giamoinhat=(currentPrice*newCount)/currentCount;
                MainActivity.arrayListCart.get(position).setPirce(giamoinhat);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                ViewHolder.txtPrice.setText("Gía : "+decimalFormat.format(giamoinhat)+" đ");
                CartActivity.eventTotal();
                if(newCount<2){
                    ViewHolder.btnValues.setText(String.valueOf(newCount));
                    ViewHolder.btnMinus.setVisibility(View.INVISIBLE);
                    ViewHolder.btnPlus.setVisibility(View.VISIBLE);
                }else{
                    ViewHolder.btnValues.setText(String.valueOf(newCount));
                    ViewHolder.btnMinus.setVisibility(View.VISIBLE);
                    ViewHolder.btnPlus.setVisibility(View.VISIBLE);
                }
            }
        });
        return convertView;
    }
}
