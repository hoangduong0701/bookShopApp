package com.example.bookShop.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bookShop.MainUserActivity;
import com.example.bookShop.R;
import com.example.bookShop.admin.fragment.AddBookRootFragment;
import com.example.bookShop.admin.fragment.ChartFragment;
import com.example.bookShop.admin.fragment.CustomerFragment;
import com.example.bookShop.admin.fragment.HomeFragment;
import com.example.bookShop.admin.fragment.OrderFragment;
import com.example.bookShop.authencation.LoginActivity;
import com.example.bookShop.units.PreferenceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainAdminActivity extends AppCompatActivity {
    AddBookRootFragment addBookFragment;
    ChartFragment chartFragment;
    CustomerFragment customerFragment;
    HomeFragment homeFragment;
    OrderFragment orderFragment;
    BottomNavigationView bottomNavigationView;
    ImageButton main_menu_btn;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        setContent();
        bottomNavigationView();
    }
    void bottomNavigationView() {

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homeNav) {

                    displayFragment(homeFragment);
                }
                if (item.getItemId() == R.id.bookNav) {
                    displayFragment(addBookFragment);

                }
                if (item.getItemId() == R.id.customerNav) {

                    displayFragment(customerFragment);

                }

                if (item.getItemId() == R.id.donHangNav) {

                    displayFragment(orderFragment);

                }
                if (item.getItemId() == R.id.chartNav) {

                    displayFragment(chartFragment);

                }
                return true;

            }
        });
        bottomNavigationView.setSelectedItemId(R.id.homeNav);
    }
    void setContent(){
        addBookFragment = new AddBookRootFragment();
        chartFragment = new ChartFragment();
        customerFragment = new CustomerFragment();
        homeFragment = new HomeFragment();
        orderFragment = new OrderFragment();
        main_menu_btn = findViewById(R.id.main_menu_btn);
        preferenceManager = new PreferenceManager(this);
       main_menu_btn.setOnClickListener(v -> {
           signOut();
       });


    }
    void signOut(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sign_out);
        Drawable customBackground  = ContextCompat.getDrawable(this, R.drawable.dialog_backgroud);
        dialog.getWindow().setBackgroundDrawable(customBackground);
        TextView cancelBtn = dialog.findViewById(R.id.cancelBtn);
        TextView accept = dialog.findViewById(R.id.successBtn);
        cancelBtn.setOnClickListener(v -> {
            dialog.dismiss();
        });
        accept.setOnClickListener(v -> {
            preferenceManager.clear();
            dialog.dismiss();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        dialog.show();
    }
    private void displayFragment(Fragment fragment) {
        // hien thi fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment);
        transaction.hide(addBookFragment);
        transaction.hide(chartFragment);
        transaction.hide(customerFragment);
        transaction.hide(homeFragment);
        transaction.hide(orderFragment);
        transaction.show(fragment);
        transaction.commit();
    }
}