package com.example.bookShop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookShop.R;
import com.example.bookShop.adapter.RootBookHorizontalAdapter;
import com.example.bookShop.model.MainRootBookModel;
import com.example.bookShop.model.ViewBookModel;
import com.example.bookShop.units.DataBase;

import java.util.ArrayList;
import java.util.List;

public class MainHomeFragment extends Fragment {

    RecyclerView recyclerViewMain;
    RootBookHorizontalAdapter adapter;

    DataBase dataBase;
    public MainHomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        recyclerViewMain = view.findViewById(R.id.recyclerViewMain);
        adapter = new RootBookHorizontalAdapter(getContext());
        dataBase = new DataBase(requireActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewMain.setLayoutManager(linearLayoutManager);
        adapter.setData(getDataBook());
        recyclerViewMain.setAdapter(adapter);

        return view;
    }

    private List<MainRootBookModel> getDataBook(){
        List<MainRootBookModel> rootBook = new ArrayList<>();
        List<ViewBookModel> viewBook = dataBase.getBookHot("Sách bán chạy");
        List<ViewBookModel> viewNewBook = dataBase.getBookHot("Sách mới xuất bản");
        List<ViewBookModel> viewBookPrize = dataBase.getBookHot("Sách được giải thưởng");
        List<ViewBookModel> viewBookComing = dataBase.getBookHot("Sách sắp xuất bản");
        MainRootBookModel mainRootBookModel = new MainRootBookModel("Sách bán chạy", viewBook);
        MainRootBookModel newBookModel = new MainRootBookModel("Sách mới xuất bản", viewNewBook);
        MainRootBookModel viewBookPrizeModel = new MainRootBookModel("Sách được giải thưởng", viewBookPrize);
        MainRootBookModel viewBookComingModel = new MainRootBookModel("Sách được giải thưởng", viewBookComing);
        rootBook.add(mainRootBookModel);
        rootBook.add(newBookModel);
        rootBook.add(viewBookPrizeModel);
        rootBook.add(viewBookComingModel);
        return rootBook;
    }
}