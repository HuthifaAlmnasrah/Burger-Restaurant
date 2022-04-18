package com.example.burger.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.burger.Activites.MainActivity;
import com.example.burger.Activites.OrdersActivity;
import com.example.burger.Adapters.OrderAdapter;
import com.example.burger.Models.Item;
import com.example.burger.R;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OrdersActivity.setTitle(getResources().getString(R.string.orders));

        Bundle bundle = this.getArguments();
        bundle.getSerializable("items");
        ArrayList<Item> items = (ArrayList<Item>) bundle.getSerializable("items");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        GridView gridView = view.findViewById(R.id.grid_view);

        OrderAdapter adapter = new OrderAdapter(getContext(), R.layout.order_item, items);

        gridView.setAdapter(adapter);

        ImageView check = view.findViewById(R.id.btn_check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = 0;
                int total = 0;
                for (Item item : items) {
                    sum = item.getCount()*Integer.parseInt(item.getPrice());
                    total += sum;
                }

                Bundle bundle = new Bundle();
                bundle.putInt("total",total);
                CheckFragment fragment = new CheckFragment();
                fragment.setArguments(bundle);
                if(total == 0){
                    Toast.makeText(getContext(), getResources().getString(R.string.noselected), Toast.LENGTH_SHORT).show();
                }else{
                    getParentFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
                }
            }
        });

        return view;
    }
}