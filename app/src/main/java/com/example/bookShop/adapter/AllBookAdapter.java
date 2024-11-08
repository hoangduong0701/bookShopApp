package com.example.bookShop.adapter;

import static androidx.core.app.ActivityCompat.requestPermissions;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bookShop.ProductDetailsActivity;
import com.example.bookShop.R;
import com.example.bookShop.model.ViewBookModel;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.example.bookShop.units.PreferenceManager;

import java.util.List;

public class AllBookAdapter extends RecyclerView.Adapter<AllBookAdapter.AllBookViewHolder>{

    Context context;
    List<ViewBookModel> dataList;
    DataBase dataBase;
    PreferenceManager preferenceManager;
    private static final int REQUEST_CALL_PHONE = 1;
    public AllBookAdapter(Context context, List<ViewBookModel> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_book, parent, false);
        return new AllBookAdapter.AllBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllBookViewHolder holder, int position) {
        dataBase = new DataBase(context);
        preferenceManager = new PreferenceManager(context);
        ViewBookModel book = dataList.get(position);
        if (book == null){
            return;
        }
        byte[] image = book.getImageBook();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.imageBook.setImageBitmap(bitmap);
        holder.name_book.setText(book.getTensach());
        holder.author.setText(book.getTacgia());
        holder.price.setText(book.getGia());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailsActivity.class);
            intent.putExtra("key_id",book.getId());
            context.startActivity(intent);
            Log.d("BookAdapter", "Book ID: " + book.getId());
        });
        holder.addCartBtn.setOnClickListener(v -> {
            String nameBook = book.getTensach();
            String author = book.getTacgia();
            String price = book.getGia();
            boolean check = dataBase.isCartExists(nameBook);
            if (!check){
                boolean insert = dataBase.insertCart(preferenceManager.getString(Constants.USERNAME), image, nameBook, author, price);
                if (insert){
                    Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(context, "Bạn đã thêm sản phẩm này rồi", Toast.LENGTH_SHORT).show();
            }
        });
        holder.contact.setOnClickListener(v -> {
            contact();
        });

    }
    void contact(){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_contact);
        Drawable customBackground  = ContextCompat.getDrawable(context, R.drawable.dialog_backgroud);
        dialog.getWindow().setBackgroundDrawable(customBackground);
        TextView cancelBtn = dialog.findViewById(R.id.cancelBtn);
        TextView accept = dialog.findViewById(R.id.successBtn);
        cancelBtn.setOnClickListener(v -> {
            dialog.dismiss();
        });
        accept.setOnClickListener(v -> {
            //makePhoneCall(Constants.PHONE_CALL);
            dialog.dismiss();
        });
        dialog.show();
    }

    private void makePhoneCall(String phoneNumber) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            context.startActivity(callIntent);
    }


    @Override
    public int getItemCount() {
        if (dataList != null){
            return dataList.size();
        }
        return 0;
    }

    public static class AllBookViewHolder extends RecyclerView.ViewHolder{
        ImageView imageBook;
        TextView name_book, author, price, contact ;
        ImageButton addCartBtn;
        public AllBookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.imageBook);
            name_book = itemView.findViewById(R.id.name_book);
            author = itemView.findViewById(R.id.author);
            price = itemView.findViewById(R.id.price);
            addCartBtn = itemView.findViewById(R.id.addCartBtn);
            contact = itemView.findViewById(R.id.contact);
        }
    }
}
