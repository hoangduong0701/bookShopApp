package com.example.bookShop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookShop.model.BookModel;
import com.example.bookShop.model.OrderModel;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.example.bookShop.units.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView imageView;
    Button addBtn;
    ImageButton backBtn, chatBtn, cartBtn, shareBtn, favoriteBtn;
    TextView nameBook, nameAuthor,numberPage, date, kind,object, note, price, rank;
    DataBase dataBase;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        setContent();
        getData();
        setClick();
    }

    private void setClick(){
        addBtn.setOnClickListener(v -> {
            Intent intent = getIntent();
            if (intent != null && intent.hasExtra("key_id")) {
                int bookId = intent.getIntExtra("key_id", -1);
                List<OrderModel> bookModels = dataBase.getBooksById(bookId);
                intent = new Intent(this, OrderActivity.class);
                intent.putExtra("book", (ArrayList<OrderModel>) bookModels);
                startActivity(intent);
            }

        });
        backBtn.setOnClickListener(v -> {
            onBackPressed();
        });
        favoriteBtn.setOnClickListener(v -> {
            addFavorite();
        });

    }

    private void addFavorite(){
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("key_id")) {
            byte[] imageBytes = Constants.imageViewToByte(imageView);
            String name = nameBook.getText().toString();
            String author = nameAuthor.getText().toString();
            String price_ = price.getText().toString();
            boolean check = dataBase.isNameBookExists(name);
            if (!check){
                boolean insertFavorite = dataBase.insertFavorite(preferenceManager.getString(Constants.USERNAME), imageBytes, name, author, price_ );
                if (insertFavorite){
                    Toast.makeText(this, "Đã vào yêu thích", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Bạn đã thêm sách này rồi", Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(this, "không có sản phẩm nào", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData(){
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("key_id")) {
            int bookId = intent.getIntExtra("key_id", -1); // -1 là giá trị mặc định nếu không tìm thấy key_id
            dataBase.displayDataById(bookId, imageView, nameBook, numberPage, nameAuthor, date, kind, object, note, price, rank, this);

        }

    }

    void setContent(){
        imageView = findViewById(R.id.imageBook);
        nameBook = findViewById(R.id.nameBook);
        nameAuthor = findViewById(R.id.nameAuthor);
        numberPage = findViewById(R.id.numberPage);
        date = findViewById(R.id.date);
        kind = findViewById(R.id.kind);
        object = findViewById(R.id.object);
        note = findViewById(R.id.note);
        price = findViewById(R.id.price);
        rank = findViewById(R.id.rank);
        addBtn = findViewById(R.id.addBtn);
        backBtn = findViewById(R.id.backBtn);
        chatBtn = findViewById(R.id.chatBtn);
        cartBtn = findViewById(R.id.cartBtn);
        shareBtn = findViewById(R.id.shareBtn);
        favoriteBtn = findViewById(R.id.favorite);
        dataBase = new DataBase(this);
        preferenceManager = new PreferenceManager(this);
    }
}