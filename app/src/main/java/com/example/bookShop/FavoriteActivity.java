package com.example.bookShop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.bookShop.adapter.FavoriteAdapter;
import com.example.bookShop.model.BookModel;
import com.example.bookShop.model.FavoriteModel;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.example.bookShop.units.PreferenceManager;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    ImageButton backBtn;
    RecyclerView recyclerFavorite;
    DataBase dataBase;
    PreferenceManager preferenceManager;
    FavoriteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        setContent();

        backBtn.setOnClickListener(v -> {
            onBackPressed();
        });

        recyclerFavorite.setLayoutManager(new LinearLayoutManager(this));
        List<FavoriteModel> dataList = dataBase.getFavorite(preferenceManager.getString(Constants.USERNAME));
        adapter = new FavoriteAdapter(dataList, this);
        recyclerFavorite.setAdapter(adapter);
    }

    private void setContent(){
        backBtn = findViewById(R.id.backBtn);
        recyclerFavorite = findViewById(R.id.recyclerFavorite);
        dataBase = new DataBase(this);
        preferenceManager = new PreferenceManager(this);
    }
}