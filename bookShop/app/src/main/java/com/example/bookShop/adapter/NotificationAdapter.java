package com.example.bookShop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookShop.R;
import com.example.bookShop.model.NotificationModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{

    Context context;
    List<NotificationModel> dataList;

    public NotificationAdapter(Context context, List<NotificationModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification_recycler, parent, false);
        return new NotificationAdapter.NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationModel model = dataList.get(position);
        if (model == null){
            return;
        }
        holder.type.setText(model.getLoaithongbao());
        holder.content.setText(model.getNoidungthongbao());
        holder.timer.setText(model.getThoigian());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder{
        TextView type, content,timer;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.typeNotification);
            content = itemView.findViewById(R.id.contentNotification);
            timer = itemView.findViewById(R.id.timer);
        }
    }
}
