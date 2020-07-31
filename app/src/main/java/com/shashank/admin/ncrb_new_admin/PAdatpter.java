package com.shashank.admin.ncrb_new_admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.shashank.admin.ncrb_new_admin.FirAdmin;

import java.util.ArrayList;
import java.util.List;

public class PAdatpter extends RecyclerView.Adapter<PAdatpter.programmingHolder> {


    LayoutInflater inflater;
    ArrayList<String>firNo;
    ArrayList<String>name;
    ArrayList<String>adharNo;
    Context context;

    public PAdatpter(ArrayList<String>firNo,ArrayList<String>name,ArrayList<String>adharNo,Context context){
        this.firNo = firNo;
        this.name = name;
        this.adharNo = adharNo;
        this.context=context;
    }


    @NonNull
    @Override
    public programmingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Create Views and stores in view Holder

        inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.list_item_layout,parent,false);
        return new programmingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull programmingHolder holder, int i) {
        //Bind the data with views


        holder.firNoTv.setText(firNo.get(i));
        holder.victimNameTv.setText(name.get(i));
        holder.victimAdharNoTv.setText(adharNo.get(i));
    }

    @Override
    public int getItemCount() {
        return firNo.size();
    }

    public class programmingHolder extends RecyclerView.ViewHolder {
        TextView victimAdharNoTv;
        TextView firNoTv;
        TextView victimNameTv;

        public programmingHolder(@NonNull View itemView) {
            super(itemView);
            firNoTv= itemView.findViewById(R.id.FirNumberList);
            victimNameTv= itemView.findViewById(R.id.AdharFirList);
            victimAdharNoTv= itemView.findViewById(R.id.ContactFirList);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"Name :- "+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
