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
import com.example.bookShop.model.BookModel;
import com.example.bookShop.model.FavoriteModel;
import com.example.bookShop.model.OrderModel;
import com.example.bookShop.units.DataBase;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private List<OrderModel> dataList;
    private Context context;
    DataBase dataBase;
    private TextView totalTextView;

    public OrderAdapter(List<OrderModel> dataList, Context context, TextView totalTextView) {
        this.dataList = dataList;
        this.context = context;
        this.totalTextView = totalTextView;
    }
    public List<OrderModel> getDataList() {
        return dataList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_recycler, parent, false);
        return new OrderAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        dataBase = new DataBase(context);
        OrderModel data = dataList.get(position);
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
            dataBase.deleteFavorite(d);
            removeBook(position);
        });
        holder.amount.setText(String.valueOf(data.getSoluong()));
        holder.addBtn.setOnClickListener(v -> {
            data.setSoluong(data.getSoluong() + 1);
            notifyItemChanged(position);
            calculateTotal();
        });

        holder.removeBtn.setOnClickListener(v -> {
            if (data.getSoluong() > 0) {
                data.setSoluong(data.getSoluong() - 1);
                notifyItemChanged(position);
                calculateTotal();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void removeBook(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dataList.size());
    }
    public  static  class OrderViewHolder extends RecyclerView.ViewHolder{
        ImageView imageBook;
        ImageButton deleteBtn, removeBtn, addBtn;
        TextView name_book, author, price, amount ;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.imageBook);
            name_book = itemView.findViewById(R.id.bookName);
            author = itemView.findViewById(R.id.bookAuthor);
            price = itemView.findViewById(R.id.price);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

            removeBtn = itemView.findViewById(R.id.removeBtn);
            addBtn = itemView.findViewById(R.id.addBtn);
            amount = itemView.findViewById(R.id.amount);
        }
    }
    private void calculateTotal() {
        double total = 0;
        for (OrderModel item : dataList) {
            int soLuong = item.getSoluong();
            double price = Double.parseDouble(item.getGia());

            total += soLuong * price;
        }
        totalTextView.setText(String.valueOf(total));
    }
}
