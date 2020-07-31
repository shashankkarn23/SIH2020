package com.shashank.admin.ncrb_new_admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PAdatpterFirAssign extends RecyclerView.Adapter<PAdatpterFirAssign.programmingHolder>{

    LayoutInflater inflater;
    ArrayList<String> copID;
    ArrayList<String> copName;
    ArrayList<String> copContact;
    ArrayList<String> copStatus;
    Context context;


    public PAdatpterFirAssign(ArrayList<String>copID, ArrayList<String>copName, ArrayList<String>copContact,ArrayList<String>copStatus, Context context){
        this.copID = copID;
        this.copName = copName;
        this.copContact = copContact;
        this.copStatus=copStatus;
        this.context=context;
    }


    @NonNull
    @Override
    public programmingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater= LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.list_available_item_layout,parent,false);
        return new programmingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull programmingHolder holder, int position) {

        holder.copIDTV.setText(copID.get(position));
        holder.copNameTV.setText(copName.get(position));
        holder.copContactTV.setText(copContact.get(position));
        holder.copStatusTV.setText(copStatus.get(position));

    }


    @Override
    public int getItemCount() { return copID.size(); }

    public class programmingHolder extends RecyclerView.ViewHolder {
        TextView copIDTV;
        TextView copNameTV;
        TextView copContactTV;
        TextView copStatusTV;

        public programmingHolder(@NonNull View itemView) {
            super(itemView);
            copIDTV=itemView.findViewById(R.id.CopIDAvailable);
            copNameTV=itemView.findViewById(R.id.copNameAvailable);
            copContactTV=itemView.findViewById(R.id.copPhoneAvailable);
            copStatusTV=itemView.findViewById(R.id.status);
        }
    }
}
