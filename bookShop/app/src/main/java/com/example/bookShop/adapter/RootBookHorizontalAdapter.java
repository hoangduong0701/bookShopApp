package com.example.bookShop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookShop.R;
import com.example.bookShop.model.MainRootBookModel;
import com.example.bookShop.units.DataBase;

import java.util.List;


public class RootBookHorizontalAdapter extends RecyclerView.Adapter<RootBookHorizontalAdapter.MainRootBookViewHolder> {
    DataBase dataBase;
    private Context context;
    private List<MainRootBookModel> modelList;


    public RootBookHorizontalAdapter(Context context) {
        this.context = context;

    }
    public void setData(List<MainRootBookModel> modelList){
        this.modelList = modelList;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public MainRootBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_root_book, parent, false);
        return new RootBookHorizontalAdapter.MainRootBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRootBookViewHolder holder, int position) {
        MainRootBookModel mainRootBookModel = modelList.get(position);
        if (mainRootBookModel == null){
            return;
        }
        holder.hangmuc.setText(mainRootBookModel.getHangmuc());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.recyclerViewRoot.setLayoutManager(linearLayoutManager);

        BookAdapter adapter = new BookAdapter(context);
        adapter.setData(mainRootBookModel.getBookModels());
        holder.recyclerViewRoot.setAdapter(adapter);


    }


    @Override
    public int getItemCount() {
        if (modelList != null){
            return modelList.size();
        }
        return 0;
    }


    public static class MainRootBookViewHolder extends RecyclerView.ViewHolder{
        private TextView hangmuc;
        private  RecyclerView recyclerViewRoot;
        public MainRootBookViewHolder(@NonNull View itemView) {
            super(itemView);
            hangmuc = itemView.findViewById(R.id.rankTv);
            recyclerViewRoot = itemView.findViewById(R.id.recyclerViewRoot);

        }
    }

}
