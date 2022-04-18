package com.example.burger.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.burger.Activites.OrdersActivity;
import com.example.burger.Adapters.CustomAdapter;
import com.example.burger.Models.Item;
import com.example.burger.R;

import java.util.ArrayList;

public class SnaksFragment extends Fragment {

    public SnaksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snaks, container, false);

        GridView gridView = view.findViewById(R.id.grid_view);
        ArrayList<Item> snaks = new ArrayList<>();
        snaks.add(new Item(R.drawable.chickenbucket,getString(R.string.shawerma), "5","6", 0));
        snaks.add(new Item(R.drawable.chickenbucket,getString(R.string.hotdog), "4", "6", 0));
        snaks.add(new Item(R.drawable.chickenbucket,getString(R.string.chrisipy), "6", "7", 0));
        snaks.add(new Item(R.drawable.chickenbucket,getString(R.string.faheta), "3","4",0));
        CustomAdapter adapter = new CustomAdapter(getContext(), R.layout.item, snaks);
        gridView.setAdapter(adapter);

        return view;
    }

}