package com.example.bookShop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.bookShop.fragment.ProfileFragment;
import com.example.bookShop.fragment.MainHomeFragment;
import com.example.bookShop.fragment.NewsFragment;
import com.example.bookShop.fragment.NotificationFragment;
import com.example.bookShop.fragment.StoreFragment;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.PreferenceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;


public class MainUserActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    ImageButton  menuBtn;
    NewsFragment NewsFragment;
    NotificationFragment NotificationFragment;
    ProfileFragment ProfileFragment;
    StoreFragment StoreFragment;
    MainHomeFragment mainHomeFragment;
    ImageButton favoriteBtn, cartBtn;
    PreferenceManager preferenceManager;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        drawerLayout =findViewById(R.id.drawrlayout);
        context = this;
        NewsFragment =new NewsFragment();
        StoreFragment = new StoreFragment();
        mainHomeFragment = new MainHomeFragment();
        ProfileFragment = new ProfileFragment();
        NotificationFragment = new NotificationFragment();
        Constants.check(this, this);
        menuBtn = findViewById(R.id.main_menu_btn);
        favoriteBtn = findViewById(R.id.favoriteBtn);
        cartBtn = findViewById(R.id.cartBtn);
        preferenceManager = new PreferenceManager(this);

        listenNer();
        navigationView();
        bottomNavigationView();

    }



    void listenNer(){
        menuBtn.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        favoriteBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, FavoriteActivity.class));
        });
        cartBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
        });
    }
    void navigationView(){
        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (drawerLayout != null){
                    drawerLayout.closeDrawers();
                }
                if (item.getItemId() == R.id.fictional){
                    displayFragment(NewsFragment);

                }
                return true;

            }

        });


    }
    private void displayFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment);
        transaction.hide(NewsFragment);
        transaction.hide(NotificationFragment);
        transaction.hide(StoreFragment);
        transaction.hide(mainHomeFragment);
        transaction.hide(ProfileFragment);
        transaction.show(fragment);
        transaction.commit();
    }

    void bottomNavigationView() {

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homeNav) {

                    displayFragment(mainHomeFragment);
                }
                if (item.getItemId() == R.id.orderNav) {

                    displayFragment(NewsFragment);
                }
                if (item.getItemId() == R.id.storeNav) {

                    displayFragment(StoreFragment);

                }

                if (item.getItemId() == R.id.notificationNav) {

                    displayFragment(NotificationFragment);

                }
                if (item.getItemId() == R.id.acountNav) {

                    displayFragment(ProfileFragment);

                }
                return true;

            }
        });
        bottomNavigationView.setSelectedItemId(R.id.homeNav);
    }
}