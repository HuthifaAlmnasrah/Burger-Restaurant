package com.example.burger.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.burger.Activites.MainActivity;
import com.example.burger.R;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        LinearLayout burgerSection = view.findViewById(R.id.burger_section);
        LinearLayout snaksSection = view.findViewById(R.id.snaks_section);

        burgerSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setTitle(getResources().getString(R.string.burger));
                MainActivity.showBackAndTitle();
                getParentFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new BurgerFragment()).addToBackStack(null).commit();
            }
        });
        snaksSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setTitle(getResources().getString(R.string.snaks));
                MainActivity.showBackAndTitle();
                getParentFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new SnaksFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}