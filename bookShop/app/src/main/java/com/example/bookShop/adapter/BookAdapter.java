package com.example.bookShop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookShop.ProductDetailsActivity;
import com.example.bookShop.R;
import com.example.bookShop.model.ViewBookModel;
import com.example.bookShop.units.Constants;
import com.example.bookShop.units.DataBase;
import com.example.bookShop.units.PreferenceManager;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.bookHotViewHolder> {


    private List<ViewBookModel> dataList;
    private Context context;
    DataBase dataBase;
    PreferenceManager preferenceManager;
    public BookAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ViewBookModel> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public bookHotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_book, parent, false);
        return new BookAdapter.bookHotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bookHotViewHolder holder, int position) {
        dataBase = new DataBase(context);
        preferenceManager = new PreferenceManager(context);
        ViewBookModel book = dataList.get(position);
        if (book == null) {
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
    }

    @Override
    public int getItemCount() {
        if (dataList != null){
            return dataList.size();
        }
        return 0;
    }

    public static class bookHotViewHolder extends RecyclerView.ViewHolder{

        ImageView imageBook;
        TextView name_book, author, price ;
        ImageButton addCartBtn;
        public bookHotViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.imageBook);
            name_book = itemView.findViewById(R.id.name_book);
            author = itemView.findViewById(R.id.author);
            price = itemView.findViewById(R.id.price);
            addCartBtn = itemView.findViewById(R.id.addCartBtn);

        }
    }

}
