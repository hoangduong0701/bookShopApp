package com.example.bookShop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.bookShop.adapter.CartAdapter;
import com.example.bookShop.adapter.FavoriteAdapter;
import com.example.bookShop.model.FavoriteModel;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.example.bookShop.units.PreferenceManager;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    ImageButton backBtn;
    RecyclerView recyclerCart;
    DataBase dataBase;
    PreferenceManager preferenceManager;
    CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setContent();
        backBtn.setOnClickListener(v -> {
            onBackPressed();
        });

        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        List<FavoriteModel> dataList = dataBase.getCart(preferenceManager.getString(Constants.USERNAME));
        adapter = new CartAdapter(dataList, this);
        recyclerCart.setAdapter(adapter);
    }
    private void setContent(){
        backBtn = findViewById(R.id.backBtn);
        recyclerCart = findViewById(R.id.recyclerCart);
        dataBase = new DataBase(this);
        preferenceManager = new PreferenceManager(this);
    }
}