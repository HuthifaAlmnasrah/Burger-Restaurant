package com.example.burger.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.burger.Adapters.OrderAdapter;
import com.example.burger.Fragments.BurgerFragment;
import com.example.burger.Fragments.CheckFragment;
import com.example.burger.Fragments.LocationsFragment;
import com.example.burger.Fragments.MainFragment;
import com.example.burger.Fragments.OrdersFragment;
import com.example.burger.Fragments.ProfileFragment;
import com.example.burger.Fragments.SnaksFragment;
import com.example.burger.Models.Item;
import com.example.burger.R;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_orders);

        initViews();
        title.setText("O R D E R S");
        Intent intent = getIntent();
        ArrayList<Item> items = (ArrayList<Item>) intent.getSerializableExtra("items");
        Bundle bundle = new Bundle();
        bundle.putSerializable("items", items);

        OrdersFragment ordersFragment = new OrdersFragment();
        ordersFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                ordersFragment).addToBackStack(null).commit();

    }


        @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menu_btn :
                openOrCloseDrawer();
                break;
            case R.id.back_btn:
                startActivity(new Intent(OrdersActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.profile:
                closeDrawer();
                title.setText(R.string.profile);
                title.setTextColor(getResources().getColor(R.color.ligth_green));
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new ProfileFragment()).addToBackStack(null).commit();
                break;
            case R.id.burger:
                closeDrawer();
                title.setText(R.string.burger);
                title.setTextColor(getResources().getColor(R.color.orange));
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new BurgerFragment()).addToBackStack(null).commit();
                break;
            case R.id.snaks:
                closeDrawer();
                title.setText(R.string.snaks);
                title.setTextColor(getResources().getColor(R.color.yellow));
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new SnaksFragment()).addToBackStack(null).commit();
                break;
            case R.id.orders:
                closeDrawer();
                title.setText(R.string.orders);
                title.setTextColor(getResources().getColor(R.color.ligth_green));
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new OrdersFragment()).addToBackStack(null).commit();
                break;
            case R.id.locations:
                title.setText(R.string.locations);
                title.setTextColor(getResources().getColor(R.color.ligth_green));
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new LocationsFragment()).addToBackStack(null).commit();
                break;
            case R.id.logout:
                closeDrawer();
                break;
        }
    }
    private void openOrCloseDrawer() {
        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
        }else{
            drawerLayout.openDrawer(GravityCompat.END);
        }
    }
    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.END);
    }
    public static void setTitle(String s){
        title.setText(s);
    }
}