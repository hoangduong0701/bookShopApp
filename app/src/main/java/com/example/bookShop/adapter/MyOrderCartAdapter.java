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
import com.example.bookShop.model.OrderCartModel;

import java.util.List;

public class MyOrderCartAdapter extends RecyclerView.Adapter<MyOrderCartAdapter.MyOrderCartViewHolder>{
    Context context;
    List<OrderCartModel> dataList;

    public MyOrderCartAdapter(Context context, List<OrderCartModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyOrderCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_cart_recycler, parent, false);
        return new MyOrderCartAdapter.MyOrderCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderCartViewHolder holder, int position) {
        OrderCartModel model = dataList.get(position);
        if (model == null){
            return;
        }
        byte[] image = model.getImageBook();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.imageBook.setImageBitmap(bitmap);
        holder.name_book.setText(model.getTensach());
        holder.author.setText(model.getTacgia());
        holder.price.setText(model.getGia());
        holder.amount.setText(model.getTongtien());
        holder.quantity.setText(String.valueOf(model.getSoluong()));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyOrderCartViewHolder extends RecyclerView.ViewHolder{
        ImageView imageBook;
        TextView name_book, author, price, amount, quantity;
        public MyOrderCartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.imageBook);
            name_book = itemView.findViewById(R.id.bookName);
            author = itemView.findViewById(R.id.bookAuthor);
            price = itemView.findViewById(R.id.price);
            amount = itemView.findViewById(R.id.money);
            quantity = itemView.findViewById(R.id.quantity);



        }
    }
}
