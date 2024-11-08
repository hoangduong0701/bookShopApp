package com.example.bookShop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookShop.R;
import com.example.bookShop.model.BookModel;
import com.example.bookShop.units.Constants;

import java.util.List;

public class addBooksAdapter extends RecyclerView.Adapter<addBooksAdapter.addBookHolder> {

    private List<BookModel> dataList;
    private Context context;

    public addBooksAdapter(List<BookModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public addBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_book, parent, false);
        return new addBookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull addBookHolder holder, int position) {
        BookModel data = dataList.get(position);
        if (data.getImageBook() != null){
            byte[] image = data.getImageBook();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.imageView.setImageBitmap(bitmap);
            holder.nameBook.setText(data.getTensach());
            holder.nameAuthor.setText(data.getTacgia());
            holder.numberPage.setText(data.getSotrang());
            holder.date.setText(data.getPhathanh());
            holder.kind.setText(data.getLoaisach());
            holder.object.setText(data.getDoituong());
            holder.note.setText(data.getGhichu());
            holder.price.setText(data.getGia());
            holder.rank.setText(data.getHangmuc());
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class addBookHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameBook, nameAuthor,numberPage, date, kind,object, note, price, rank;

        public addBookHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.imageBook);
            nameBook = view.findViewById(R.id.nameBook);
            nameAuthor = view.findViewById(R.id.nameAuthor);
            numberPage = view.findViewById(R.id.numberPage);
            date = view.findViewById(R.id.date);
            kind = view.findViewById(R.id.kind);
            object = view.findViewById(R.id.object);
            note = view.findViewById(R.id.note);
            price = view.findViewById(R.id.price);
            rank = view.findViewById(R.id.rank);


        }
    }
}
