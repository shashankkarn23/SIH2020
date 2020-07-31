package com.shashank.admin.ncrb_new_admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private List<Notification> list;
    private List<Notification> allList;

    public NotificationAdapter(Context context, List<Notification> list) {
        this.context = context;
        this.list = list;
        this.allList = new ArrayList<>(list);
    }
    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notification_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationAdapter.ViewHolder holder, final int position) {
        final Notification notification = list.get(position);
        holder.text_name.setText(notification.getName());
        holder.text_age.setText(notification.getAge());
        holder.text_crime.setText(notification.getCrime());
        Glide.with(context).load(notification.getImageUrl()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to show image in full screen:
                new PhotoFullPopupWindow(context, R.layout.imageview_layout, v, notification.getImageUrl(), null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView text_name,text_age,text_crime,more;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_name = itemView.findViewById(R.id.Name_txt);
            text_age = itemView.findViewById(R.id.Age_txt);
            text_crime = itemView.findViewById(R.id.Crime_txt);
            image = itemView.findViewById(R.id.imageView2);
        }
    }
}
