package com.example.bookShop.admin.fragment.addbook;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bookShop.R;
import com.example.bookShop.model.BookModel;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import com.example.bookShop.adapter.addBooksAdapter;


public class AddBooksFragment extends Fragment {
    DataBase dataBase;
    AutoCompleteTextView kindBook, doiTuongEdt, rankEdt;
    RelativeLayout view1;
    ImageView imageBook;
    Button addBtn, updateBtn, deleteBtn;
    TextInputLayout dateTip;
    TextInputEditText nameBookEdt, authorEdt, numberPageEdt, dateEdt , priceEdt, noteEdt;
    private static final int PICK_IMAGE = 1;
    addBooksAdapter adapter;
    RecyclerView lv;
    private byte[] imageByteArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_book2, container, false);
        kindBook = view.findViewById(R.id.kindBookEdt);
        doiTuongEdt = view.findViewById(R.id.doituongEdt);
        view1 = view.findViewById(R.id.view1);
        imageBook = view.findViewById(R.id.imageBook);
        nameBookEdt = view.findViewById(R.id.nameBookEdt);
        authorEdt = view.findViewById(R.id.authorEdt);
        numberPageEdt = view.findViewById(R.id.numberPageEdt);
        dateEdt = view.findViewById(R.id.dateEdt);
        dateTip = view.findViewById(R.id.dateTip);
        noteEdt = view.findViewById(R.id.noteEdt);
        priceEdt = view.findViewById(R.id.priceEdt);
        rankEdt = view.findViewById(R.id.rankEdt);
        lv = view.findViewById(R.id.lv);
        addBtn = view.findViewById(R.id.addBtn);
        updateBtn = view.findViewById(R.id.updateBtn);
        deleteBtn = view.findViewById(R.id.deleteBtn);
        dataBase = new DataBase(getContext());
        setClick();

        lv.setLayoutManager(new LinearLayoutManager(getContext()));
        List<BookModel> dataList = dataBase.getAllData();
        adapter = new addBooksAdapter(dataList, getContext());
        lv.setAdapter(adapter);
        getKindBookName();
        getDoiTuong();
        getRank();

        return view;
    }

    void setClick(){
        view1.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });
        dateTip.setEndIconOnClickListener(v -> {
            getDate(dateEdt);
        });
        addBtn.setOnClickListener(v -> {
            addBook(imageByteArray);
        });


    }


    void addBook(byte[] image){
        String nameBook = nameBookEdt.getText().toString();
        String author = authorEdt.getText().toString();
        String numberPage = numberPageEdt.getText().toString();
        String kind = kindBook.getText().toString();
        String date = dateEdt.getText().toString();
        String  obj = doiTuongEdt.getText().toString();
        String price = priceEdt.getText().toString();
        String note = noteEdt.getText().toString();
        String rank = rankEdt.getText().toString();

        boolean isValid = Constants.validateFields(nameBookEdt, authorEdt, numberPageEdt, priceEdt, dateEdt, noteEdt,
                kindBook, doiTuongEdt, nameBook, author, numberPage, kind, date, obj, price, note);

        if (isValid)
        {
            boolean insert = dataBase.insertBook(image, nameBook, author, numberPage, kind, date, obj, note,rank, price);
            if (insert){
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /// mo abumm anh va lay du lieu anh
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageBook.setImageURI(selectedImage);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                imageByteArray = stream.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    void getDate(TextInputEditText dateTime){
        MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Chọn ngày phát hành")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(selection));
                dateTime.setText(date);
            }
        });
        materialDatePicker.show(getChildFragmentManager(), "tag_date");
    }
    void getKindBookName(){
        List<String> names = dataBase.getAllKindBook();
        if (names.isEmpty()){
            kindBook.setText("Không có dữ liệu");
        }else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                    android.R.layout.simple_dropdown_item_1line, names);
            kindBook.setAdapter(adapter);
        }
    }
    void getDoiTuong(){
        List<String> doiTuong = new ArrayList<>();
        doiTuong.add("Em bé (03 - 06)");
        doiTuong.add("Trẻ em (06 - 10)");
        doiTuong.add("Thiếu nhi (10 - 15)");
        doiTuong.add("Tuổi mới lớn (15 - 18)");
        doiTuong.add("Trưởng thành ( 18+ )");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                    android.R.layout.simple_dropdown_item_1line, doiTuong);
            doiTuongEdt.setAdapter(adapter);
        }

    void getRank(){
        List<String> rank = new ArrayList<>();
        rank.add("none");
        rank.add("Sách bán chạy");
        rank.add("Sách được giải thưởng");
        rank.add("Sách mới xuất bản");
        rank.add("Sách sắp xuất bản");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                android.R.layout.simple_dropdown_item_1line, rank);
        rankEdt.setAdapter(adapter);
    }
}