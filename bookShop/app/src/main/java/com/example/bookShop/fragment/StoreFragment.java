package com.example.bookShop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookShop.R;
import com.example.bookShop.adapter.AllBookAdapter;
import com.example.bookShop.model.ViewBookModel;
import com.example.bookShop.units.DataBase;

import java.util.List;

public class StoreFragment extends Fragment {
    AllBookAdapter adapter;
    RecyclerView recyclerView;
    DataBase dataBase;
    public StoreFragment() {
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
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        dataBase = new DataBase(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        List<ViewBookModel> dataList = dataBase.getAllBook();
        adapter = new AllBookAdapter( getContext(), dataList);
        recyclerView.setAdapter(adapter);


        return view;
    }
}