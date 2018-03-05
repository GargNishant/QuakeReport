package com.example.nishantgarg.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nishant Garg on 05-03-2018.
 */

public class QuakeArrayAdapter extends RecyclerView.Adapter<QuakeArrayAdapter.ViewHolder> {
    private static List<Quakes> quakesList = new ArrayList<>();

    public QuakeArrayAdapter(List<Quakes> arrayList){
        quakesList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutID = R.layout.list;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutID,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.magView.setText(String.valueOf(quakesList.get(position).getmMagnitude()));
        holder.main_placeView.setText(String.valueOf(quakesList.get(position).getMainPlace()));
        holder.sub_placeView.setText(String.valueOf(quakesList.get(position).getSubPlace()));
        holder.timeView.setText(String.valueOf(quakesList.get(position).getTime()));
        holder.dateView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return quakesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView magView, sub_placeView, main_placeView, dateView, timeView;

        public ViewHolder(View itemView) {
            super(itemView);
            magView = (TextView)itemView.findViewById(R.id.magnitude);
            sub_placeView = (TextView)itemView.findViewById(R.id.sub_place);
            main_placeView = (TextView)itemView.findViewById(R.id.main_place);
            dateView = (TextView)itemView.findViewById(R.id.date);
            timeView = (TextView)itemView.findViewById(R.id.time);
        }
    }
}