package com.example.burger.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.burger.Activites.MainActivity;
import com.example.burger.Activites.OrdersActivity;
import com.example.burger.R;

public class CheckFragment extends Fragment {

    public CheckFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OrdersActivity.setTitle(getString(R.string.check));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check, container, false);

        TextView textView = view.findViewById(R.id.total);
        Bundle bundle = getArguments();
        int total = bundle.getInt("total");
        textView.setText(total + getResources().getString(R.string.jd));


        LinearLayout order = view.findViewById(R.id.btn_order);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.frame_container, new ConfirmFragment()).commit();
            }
        });
        return view;
    }
}