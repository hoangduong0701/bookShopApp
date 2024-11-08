package com.example.bookShop.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookShop.OrderActivity;
import com.example.bookShop.OrderCartActivity;
import com.example.bookShop.R;
import com.example.bookShop.authencation.LoginActivity;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.example.bookShop.units.PreferenceManager;
import com.google.android.material.textfield.TextInputEditText;


public class ProfileFragment extends Fragment {
    PreferenceManager preferenceManager;
    TextView name;
    Button logOut;
    LinearLayout orderBtn, locationBtn;
    DataBase dataBase;

    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        preferenceManager = new PreferenceManager(getContext());
        name = view.findViewById(R.id.name);
        name.setText(preferenceManager.getString(Constants.USERNAME));
        logOut = view.findViewById(R.id.logout);
        orderBtn = view.findViewById(R.id.view5);
        locationBtn = view.findViewById(R.id.view13);
        dataBase = new DataBase(getContext());

        locationBtn.setOnClickListener(v -> {
            updateLocation();
        });
        orderBtn.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), OrderCartActivity.class));
        });
        logOut.setOnClickListener(v -> {
            signOut();
        });

        return view;
    }
    @SuppressLint("Range")
    void updateLocation(){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_location);
        Drawable customBackground  = ContextCompat.getDrawable(getContext(), R.drawable.dialog_backgroud);
        dialog.getWindow().setBackgroundDrawable(customBackground);
        TextView cancelBtn = dialog.findViewById(R.id.cancelBtn);
        TextView accept = dialog.findViewById(R.id.successBtn);
        TextInputEditText location = dialog.findViewById(R.id.locationEdt);
        TextInputEditText phone = dialog.findViewById(R.id.phoneEdt);

        cancelBtn.setOnClickListener(v -> {
            dialog.dismiss();
        });
        accept.setOnClickListener(v -> {
            String diaChi = location.getText().toString();
            String sdt = phone.getText().toString();

            if (!diaChi.isEmpty() && !sdt.isEmpty()){
              boolean update = dataBase.updateLocationAndPhone(preferenceManager.getString(Constants.USERNAME), diaChi, sdt);
              if (update){
                  Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
              }
            }else {
                Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        Cursor cursor = dataBase.getAccountData(preferenceManager.getString(Constants.USERNAME));
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String diachi = cursor.getString(cursor.getColumnIndex(Constants.LOCATION));
                String sdt = cursor.getString(cursor.getColumnIndex(Constants.PHONE_NUMBER));

                location.setText(diachi);
                phone.setText(sdt);
            }
            cursor.close();
        }
        dialog.show();
    }
    void signOut(){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_sign_out);
        Drawable customBackground  = ContextCompat.getDrawable(getContext(), R.drawable.dialog_backgroud);
        dialog.getWindow().setBackgroundDrawable(customBackground);
        TextView cancelBtn = dialog.findViewById(R.id.cancelBtn);
        TextView accept = dialog.findViewById(R.id.successBtn);
        cancelBtn.setOnClickListener(v -> {
            dialog.dismiss();
        });
        accept.setOnClickListener(v -> {
            preferenceManager.clear();
            dialog.dismiss();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
        dialog.show();
    }
}