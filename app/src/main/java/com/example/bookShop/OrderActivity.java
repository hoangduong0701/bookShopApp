package com.example.bookShop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookShop.adapter.OrderAdapter;
import com.example.bookShop.model.BookModel;
import com.example.bookShop.model.OrderModel;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.example.bookShop.units.PreferenceManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerOrder;
    TextInputLayout locationTip, cardTip;
    AutoCompleteTextView locationEdt, cardEdt;
    TextInputEditText noteEdt, phoneEdt;
    TextView tongTien;
    Button orderBtn;
    DataBase dataBase;
    PreferenceManager preferenceManager;
    OrderAdapter adapter;
    ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        dataBase = new DataBase(this);
        setContent();
        getLocation();
        getThanhToan();
        getDataPersonal();
        recyclerOrder.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("book")) {
            @SuppressWarnings("unchecked")
            ArrayList<OrderModel> bookModels = (ArrayList<OrderModel>) intent.getSerializableExtra("book");
            if (bookModels != null) {
                adapter = new OrderAdapter(bookModels, this, tongTien);
                recyclerOrder.setAdapter(adapter);
            }
        }

        orderBtn.setOnClickListener(v -> {
            String location = locationEdt.getText().toString();
            String phone = phoneEdt.getText().toString();
            String card = cardEdt.getText().toString();
            String total = tongTien.getText().toString();
            String note = noteEdt.getText().toString();
            List<OrderModel> orders = adapter.getDataList();
            for (OrderModel order : orders) {
                boolean insert =  dataBase.insertOrder(preferenceManager.getString(Constants.USERNAME),
                        order.getImageBook(), order.getTensach(), order.getTacgia(), order.getGia(),
                        Constants.getCurrentTime(), order.getSoluong(), total, card, location, phone, note);

                if (insert){
                    Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
                }
            }
            boolean insert = dataBase.insertNotification("Đặt hàng của bạn!", "Bạn đã đặt hàng thành công", Constants.getCurrentTime(), preferenceManager.getString(Constants.USERNAME));

            if (insert){
                //Toast.makeText(this, "Thông báo thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Thông báo thất bại", Toast.LENGTH_SHORT).show();
            }
        });
        backBtn.setOnClickListener(v -> {
            onBackPressed();
        });
    }




    private void setContent(){
        recyclerOrder = findViewById(R.id.recyclerOrder);
        locationTip = findViewById(R.id.locationTip);
        cardTip = findViewById(R.id.cardTip);
        locationEdt = findViewById(R.id.locationEdt);
        cardEdt = findViewById(R.id.cardEdt);
        noteEdt = findViewById(R.id.noteEdt);
        tongTien = findViewById(R.id.tongTien);
        orderBtn = findViewById(R.id.orderBtn);
        phoneEdt = findViewById(R.id.phoneEdt);
        preferenceManager = new PreferenceManager(this);
        backBtn = findViewById(R.id.backBtn);



    }
    void getLocation(){
        List<String> names = dataBase.getLocation();
        if (names.isEmpty()){
            locationEdt.setText("Không có dữ liệu");
        }else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, names);
            locationEdt.setAdapter(adapter);
        }
    }
    void getThanhToan(){
        List<String> rank = new ArrayList<>();
        rank.add("Thanh toán sau khi nhận hàng");
        rank.add("Thanh toán online");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, rank);
        cardEdt.setAdapter(adapter);
    }
    @SuppressLint("Range")
    void getDataPersonal() {
        Cursor cursor = dataBase.getAccountData(preferenceManager.getString(Constants.USERNAME));
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String diachi = cursor.getString(cursor.getColumnIndex(Constants.LOCATION));
                String sdt = cursor.getString(cursor.getColumnIndex(Constants.PHONE_NUMBER));

                locationEdt.setText(diachi);
                phoneEdt.setText(sdt);
            }
            cursor.close();
        }
    }
}