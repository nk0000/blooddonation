package com.example.blooddonation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    Context context;
    ArrayList<details> List;
    adapter(Activity context, ArrayList<details> List){
        this.context=context;
        this.List=List;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.blooddetail, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        details d=List.get(position);
        holder.nam.setText(d.getName());
        holder.ph.setText(d.getPhone());
        holder.bldgp.setText(d.getBloodGroup());
        holder.cty.setText(d.getCity());
        holder.medical.setText(d.getMedicalIssues());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nam;
        TextView ph;
        TextView bldgp;
        TextView cty;
        TextView medical;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nam=(TextView) itemView.findViewById(R.id.name);
            ph=(TextView) itemView.findViewById(R.id.phone);
            bldgp=(TextView) itemView.findViewById(R.id.bg);
            cty=(TextView) itemView.findViewById(R.id.city);
            medical=(TextView) itemView.findViewById(R.id.mi);
        }
    }

}
