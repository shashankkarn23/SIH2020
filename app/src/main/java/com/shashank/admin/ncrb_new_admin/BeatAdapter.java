package com.shashank.admin.ncrb_new_admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BeatAdapter extends RecyclerView.Adapter<BeatAdapter.ViewHolder>{
    private Context context;
    private List<Beat> list;

    public BeatAdapter(Context context, List<Beat> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public BeatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.beat_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final BeatAdapter.ViewHolder holder,final int position) {
        Beat beat = list.get(position);

        holder.text_area.setText(beat.getArea());
        holder.text_date.setText(beat.getDate());
        holder.text_time.setText(beat.getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, holder.text_area.getText().toString(), Toast.LENGTH_SHORT).show();
                String areaName = holder.text_area.getText().toString();
                String date = holder.text_date.getText().toString();
                String time = holder.text_time.getText().toString();
                String method = "12345";
                BeatInfo beatInfo = new BeatInfo(context);
                beatInfo.execute(method,areaName,date,time);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView text_area,text_date,text_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_area = itemView.findViewById(R.id.area_name);
            text_date = itemView.findViewById(R.id.date);
            text_time = itemView.findViewById(R.id.time);
        }
    }
}
