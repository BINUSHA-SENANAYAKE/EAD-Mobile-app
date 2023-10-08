package com.example.sliit_travel_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class scheduleAdapter extends RecyclerView.Adapter<scheduleViewHolder> {

    Context context;
    List<schedules_item> items;

    public scheduleAdapter(Context context, List<schedules_item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public scheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new scheduleViewHolder(LayoutInflater.from(context).inflate(R.layout.schedule_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull scheduleViewHolder holder, int position) {
        holder.textViewFrom.setText(items.get(position).getFrom());
        holder.textViewTo.setText(items.get(position).getTo());
        holder.textViewDepartureTime.setText(items.get(position).getDeparture());
        holder.textViewArrivalTime.setText(items.get(position).getArrival());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
