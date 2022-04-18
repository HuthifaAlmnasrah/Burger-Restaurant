package com.example.burger.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.burger.Fragments.BurgerFragment;
import com.example.burger.Fragments.LocationsFragment;
import com.example.burger.Fragments.MainFragment;
import com.example.burger.Fragments.OrdersFragment;
import com.example.burger.Fragments.ProfileFragment;
import com.example.burger.Fragments.SnaksFragment;
import com.example.burger.Models.Item;
import com.example.burger.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ImageView menu;
    public static ImageView back;
    private TextView profile;
    private TextView burger;
    private TextView snaks;
    private TextView orders;
    private TextView locations;
    private TextView logout;
    private static TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        if(savedInstanceState  == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                    new MainFragment()).commit();
            hideBackAndTitle();
        }

    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawerlayout);
        menu = findViewById(R.id.menu_btn);
        menu.setOnClickListener(this);
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(this);
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(this);
        burger = findViewById(R.id.burger);
        burger.setOnClickListener(this);
        snaks = findViewById(R.id.snaks);
        snaks.setOnClickListener(this);
        orders = findViewById(R.id.orders);
        orders.setOnClickListener(this);
        locations = findViewById(R.id.locations);
        locations.setOnClickListener(this);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);
        title = findViewById(R.id.title);
    }

    @Override
    public void onBackPressed() {
        hideBackAndTitle();
        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menu_btn :
                openOrCloseDrawer();
                break;
            case R.id.back_btn:
                getSupportFragmentManager().popBackStack();
                hideBackAndTitle();
                break;
            case R.id.profile:
                closeDrawer();
                title.setText(R.string.profile);
                title.setTextColor(getResources().getColor(R.color.ligth_green));
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new ProfileFragment()).addToBackStack(null).commit();
                showBackAndTitle();
                break;
            case R.id.burger:
                closeDrawer();
                title.setText(R.string.burger);
                title.setTextColor(getResources().getColor(R.color.orange));
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new BurgerFragment()).addToBackStack(null).commit();
                showBackAndTitle();
                break;
            case R.id.snaks:
                closeDrawer();
                title.setText(R.string.snaks);
                title.setTextColor(getResources().getColor(R.color.yellow));
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new SnaksFragment()).addToBackStack(null).commit();
                showBackAndTitle();
                break;
            case R.id.orders:
                closeDrawer();
                Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
                intent.putExtra("items",new ArrayList<Item>());
                startActivity(intent);
                break;
            case R.id.locations:
                closeDrawer();
                title.setText(R.string.locations);
                title.setTextColor(getResources().getColor(R.color.ligth_green));
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new LocationsFragment()).addToBackStack(null).commit();
                showBackAndTitle();
                break;
            case R.id.logout:
                closeDrawer();
                break;
        }
    }

    public static void showBackAndTitle(){
        title.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);
    }
    public void hideBackAndTitle(){
        title.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
    }
    public static void setTitle(String s){
        title.setText(s);
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    private void openOrCloseDrawer() {
        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
        }else{
            drawerLayout.openDrawer(GravityCompat.END);
        }
    }

}