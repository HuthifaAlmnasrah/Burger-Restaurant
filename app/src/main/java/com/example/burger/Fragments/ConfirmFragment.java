package com.example.burger.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.burger.Activites.MainActivity;
import com.example.burger.Activites.OrdersActivity;
import com.example.burger.R;


public class ConfirmFragment extends Fragment {

    public ConfirmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        OrdersActivity.setTitle(getString(R.string.confirm));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm, container, false);
    }
}