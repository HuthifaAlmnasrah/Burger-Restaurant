package com.example.burger.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.burger.Adapters.CustomAdapter;
import com.example.burger.Models.Item;
import com.example.burger.R;

import java.util.ArrayList;

public class BurgerFragment extends Fragment { ;

    public BurgerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_burger, container, false);
        GridView gridView = view.findViewById(R.id.grid_view);
        ArrayList<Item> burgers = new ArrayList<>();
        burgers.add(new Item(R.drawable.fastfood,getString(R.string.cheassburger), "5","6", 0));
        burgers.add(new Item(R.drawable.fastfood,getString(R.string.bfrburger), "4", "6", 0));
        burgers.add(new Item(R.drawable.fastfood, getString(R.string.beefburger), "6","6", 0));
        burgers.add(new Item(R.drawable.fastfood,getString(R.string.mashburger), "3 ","4", 0));
        CustomAdapter adapter = new CustomAdapter(getContext(), R.layout.item, burgers);
        gridView.setAdapter(adapter);
        return view;
    }

}