package com.example.bookShop.admin.fragment.addbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookShop.R;
import com.example.bookShop.units.DataBase;

import java.util.ArrayList;


public class KindBookFragment extends Fragment {
    EditText idKind, nameKind;
    Button addBtn, deleteBtn;
    ListView lv;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> myList;
    DataBase dataBase;

    public KindBookFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kind_book, container, false);
        addBtn = view.findViewById(R.id.addBtn);
        lv = view.findViewById(R.id.lv);
        deleteBtn = view.findViewById(R.id.deleteBtn);
        idKind = view.findViewById(R.id.id);
        nameKind = view.findViewById(R.id.nameKind);
        idKind.setEnabled(false);
        dataBase = new DataBase(getContext());
        myList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, myList);
        lv.setAdapter(arrayAdapter);

        setClick();
        return  view;
    }
    void setClick(){
        disPlay();
        addBtn.setOnClickListener(v -> {
            addKindBook();
        });
        deleteBtn.setOnClickListener(v -> {
            delete();
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = myList.get(position);
                String[] parts = selectedItem.split("-");
                if (parts.length >= 2) {
                    idKind.setText(parts[0]);
                    nameKind.setText(parts[1]);


                }
            }
        });
    }
    void addKindBook(){
        String name = nameKind.getText().toString();


        if (name.isEmpty()){
            Toast.makeText(getContext(), "điền đầy đủ", Toast.LENGTH_SHORT).show();
        }else {
            boolean add = dataBase.insertKindBook(name);
            if (add)
            {
                disPlay();
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
    void delete(){
        String id = idKind.getText().toString();
        if (id.isEmpty()){
            Toast.makeText(getContext(), "Chọn loại sách cần xóa", Toast.LENGTH_SHORT).show();
        }else {
            dataBase.deleteKind(id);
            disPlay();
        }
    }
    void disPlay(){
        dataBase.query_Kind(myList, arrayAdapter);
    }
}