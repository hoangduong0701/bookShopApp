package com.example.bookShop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.bookShop.adapter.MyOrderCartAdapter;
import com.example.bookShop.model.OrderCartModel;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.example.bookShop.units.PreferenceManager;

import java.util.List;

public class OrderCartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DataBase dataBase;
    PreferenceManager preferenceManager;
    MyOrderCartAdapter adapter;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cart);
        recyclerView = findViewById(R.id.recyclerView);
        backBtn = findViewById(R.id.backBtn);
        dataBase = new DataBase(this);
        preferenceManager = new PreferenceManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<OrderCartModel> data = dataBase.getCartOrder(preferenceManager.getString(Constants.USERNAME));
        adapter = new MyOrderCartAdapter(this, data);
        recyclerView.setAdapter(adapter);

        backBtn.setOnClickListener(v -> {
            onBackPressed();
        });



    }
}