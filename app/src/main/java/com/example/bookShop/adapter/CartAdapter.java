package com.example.bookShop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookShop.R;
import com.example.bookShop.model.FavoriteModel;
import com.example.bookShop.units.DataBase;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private List<FavoriteModel> dataList;
    private Context context;
    DataBase dataBase;

    public CartAdapter(List<FavoriteModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_recycler, parent, false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        dataBase = new DataBase(context);
        FavoriteModel data = dataList.get(position);
        if (data.getImageBook() != null) {
            byte[] image = data.getImageBook();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.imageBook.setImageBitmap(bitmap);
            holder.name_book.setText(data.getTensach());
            holder.author.setText(data.getTacgia());
            holder.price.setText(data.getGia());
        }
        holder.deleteBtn.setOnClickListener(v -> {
            int delete = data.getId();
            String d = Integer.toString(delete);
            dataBase.deleteCart(d);
            removeBook(position);
        });
    }

    @Override
    public int getItemCount() {
        if (dataList != null){
            return dataList.size();
        }
        return 0;
    }
    public void removeBook(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dataList.size());
    }
    public static class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView imageBook;
        ImageButton deleteBtn, cartBtn;
        TextView name_book, author, price ;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.imageBook);
            name_book = itemView.findViewById(R.id.bookName);
            author = itemView.findViewById(R.id.bookAuthor);
            price = itemView.findViewById(R.id.price);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            cartBtn = itemView.findViewById(R.id.buyBtn);
        }

    }
}
