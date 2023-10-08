package com.example.sliit_travel_app;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class scheduleViewHolder extends RecyclerView.ViewHolder {

    TextView textViewFrom;
    TextView textViewTo;
    TextView textViewDepartureTime;
    TextView textViewArrivalTime;

    public scheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewFrom = itemView.findViewById(R.id.train_from);
        textViewTo = itemView.findViewById(R.id.train_to);
        textViewDepartureTime = itemView.findViewById(R.id.departureTime);
        textViewArrivalTime = itemView.findViewById(R.id.arrivalTime);
    }
}
