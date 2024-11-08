package com.example.bookShop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookShop.R;
import com.example.bookShop.adapter.NotificationAdapter;
import com.example.bookShop.model.NotificationModel;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.example.bookShop.units.PreferenceManager;

import java.util.List;

public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;
    NotificationAdapter adapter;
    DataBase dataBase;
    PreferenceManager preferenceManager;
    public NotificationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = view.findViewById(R.id.recyclerNotification);
        dataBase = new DataBase(getContext());
        preferenceManager = new PreferenceManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<NotificationModel> models = dataBase.getNotification(preferenceManager.getString(Constants.USERNAME));
        adapter = new NotificationAdapter(getContext(), models);
        recyclerView.setAdapter(adapter);
        return view;
    }
}