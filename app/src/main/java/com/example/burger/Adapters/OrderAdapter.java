package com.example.burger.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.burger.Models.Item;
import com.example.burger.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderAdapter extends ArrayAdapter<Item> {
    Context mcontext;
    int mResourse;

    public OrderAdapter(Context context, int order_item, ArrayList<Item> orders) {
        super(context, order_item, orders);
        mcontext = context;
        mResourse = order_item;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int image = getItem(position).getImage();
        String type = getItem(position).getName();
        String price = getItem(position).getPrice();
        int count = getItem(position).getCount();

        Item order = new Item(image, type, price, count);

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mResourse, parent, false);

        ImageView img_type = convertView.findViewById(R.id.img_type);
        TextView txt_type = convertView.findViewById(R.id.txt_type);
        TextView txt_price = convertView.findViewById(R.id.txt_price);
        TextView txt_count = convertView.findViewById(R.id.txt_count);
        TextView plus = convertView.findViewById(R.id.plus);
        TextView minus= convertView.findViewById(R.id.minus);

        img_type.setImageResource(order.getImage());
        txt_type.setText(order.getName());
        txt_price.setText(order.getPrice()+ mcontext.getResources().getString(R.string.jd));
        txt_count.setText(order.getCount()+"");

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItem(position).setCount(order.getCount() + 1);
                order.setCount(order.getCount() + 1);
                txt_count.setText(order.getCount()+"");
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (order.getCount() > 0){
                    getItem(position).setCount(order.getCount() - 1);
                    order.setCount(order.getCount() - 1);
                    txt_count.setText(order.getCount()+"");
                }
            }
        });
        return convertView;
    }
}