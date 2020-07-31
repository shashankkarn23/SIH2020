package com.shashank.admin.ncrb_new_admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VariAdapter extends RecyclerView.Adapter<VariAdapter.ViewHolder> {

    private Context context;
    private List<Vari> list;

    public VariAdapter(Context context, List<Vari> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VariAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final VariAdapter.ViewHolder holder, final int position) {
        Vari vari = list.get(position);

        holder.text_reg_no.setText(vari.getReg_no());
        holder.text_name.setText(vari.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg_TL = MainActivityVerification.type;
                String reg_no_ack = holder.text_reg_no.getText().toString();
                String method = "var_check";
                BackgroundTask backgroundTask = new BackgroundTask(context);
                backgroundTask.execute(method, reg_TL,reg_no_ack);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView text_reg_no, text_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_reg_no = itemView.findViewById(R.id.Reg_no);
            text_name = itemView.findViewById(R.id.name);
        }
    }
}
