package com.example.bookShop.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.bookShop.R;


public class NewsFragment extends Fragment {

    LinearLayout link1, link2, link3 , link4, link5;

    public NewsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        link1 = view.findViewById(R.id.link1);
        link2 = view.findViewById(R.id.link2);
        link3 = view.findViewById(R.id.link3);
        link4 = view.findViewById(R.id.link4);
        link5 = view.findViewById(R.id.link5);

        link1.setOnClickListener(v -> {
            String url = "https://nhanam.vn/su-kien-ra-mat-cuon-sach-chip-war-cuoc-chien-vi-mach-ve-lai-ban-do-ban-dan-toan-cau-viet-nam-o-dau";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
        link2.setOnClickListener(v -> {
            String url = "https://nhanam.vn/doc-gia-hai-mien-nam-bac-no-nuc-den-hoi-sach-nha-nam-chao-he-2024";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
        link3.setOnClickListener(v -> {
            String url = "https://nhanam.vn/hoi-sach-nha-nam-chao-he-2024";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
        link4.setOnClickListener(v -> {
            String url = "https://nhanam.vn/tin-ban-quyen-until-august-tac-pham-chua-xuat-ban-cua-tac-gia-tram-nam-co-don";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
        link5.setOnClickListener(v -> {
            String url = "https://nhanam.vn/toa-dam-ra-mat-tac-pham-dia-ly-hanh-chinh-va-tap-quan-cua-nguoi-viet";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        return view;
    }
}