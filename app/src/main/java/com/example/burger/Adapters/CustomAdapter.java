package com.example.burger.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.burger.Activites.MainActivity;
import com.example.burger.Activites.OrdersActivity;
import com.example.burger.Models.Item;
import com.example.burger.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

public class CustomAdapter extends ArrayAdapter<Item> {
    Context mContext;
    int mResource;
    ArrayList<Item> items;

    int [] backGrounds = {R.drawable.iteam_two, R.drawable.iteam_three, R.drawable.iteam_one};
    int counter = 0;

    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        items = new ArrayList<>();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int image = getItem(position).getImage();
        String name = getItem(position).getName();
        String price = getItem(position).getPrice();
        String mealPrice = getItem(position).getMealPrice();
        int count = getItem(position).getCount();

        Item item = new Item(image, name, price, mealPrice, count);
        items.add(item);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        if(position %2 != 0){
            convertView.setPadding(0,150,0,0);
        }else{
            convertView.setPadding(0,0,0,0);
        }

        RadioButton button1 = convertView.findViewById(R.id.btn1);
        TextView txtPrice = convertView.findViewById(R.id.price);
        TextView txtMealPrice = convertView.findViewById(R.id.meal_price);
        TextView txtCount = convertView.findViewById(R.id.count);
        TextView plus = convertView.findViewById(R.id.plus);
        TextView minus= convertView.findViewById(R.id.minus);
        LinearLayout linear = convertView.findViewById(R.id.linear);

        if(counter < 3){
            linear.setBackgroundResource(backGrounds[counter]);
            counter++;
        }else{
            counter = 0;
        }

        button1.setText(item.getName());
        txtPrice.setText(item.getPrice()+mContext.getResources().getString(R.string.jd));
        txtMealPrice.setText(item.getMealPrice()+mContext.getResources().getString(R.string.jd));
        txtCount.setText(item.getCount()+"");

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setCount(item.getCount() + 1);
                txtCount.setText(item.getCount()+"");
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getCount() > 0){
                    item.setCount(item.getCount() - 1);
                    txtCount.setText(item.getCount()+"");
                }
            }
        });


        ArrayList<Item> list = new ArrayList<>();
        for (Item i : items) {
            if(i.getCount() > 0){
                list.add(i);
            }
        }

        MainActivity.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OrdersActivity.class);
                intent.putExtra("items",list);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

}