package com.example.sliit_travel_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class scheduleAdapter extends RecyclerView.Adapter<scheduleAdapter.ViewHolder> {

    Context context;
    ArrayList<scheduleServiceList> scheduleServiceList;

    public scheduleAdapter(Context context , ArrayList<scheduleServiceList> arrayList) {
        this.context = context;
        this.scheduleServiceList = arrayList;
    }

    @NonNull
    @Override
    public scheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_view,parent,false);
        return new ViewHolder(view);
       // return new scheduleAdapter(LayoutInflater.from(context).inflate(R.layout.schedule_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull scheduleAdapter.ViewHolder holder, int position) {
        holder.bind(scheduleServiceList.get(position));
    }

    @Override
    public int getItemCount() {
        return scheduleServiceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewFrom,textViewTo,textViewDepartureTime,textViewArrivalTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFrom = itemView.findViewById(R.id.train_from);
            textViewTo = itemView.findViewById(R.id.train_to);
            textViewDepartureTime = itemView.findViewById(R.id.departureTime);
            textViewArrivalTime = itemView.findViewById(R.id.arrivalTime);
        }
        public void bind(scheduleServiceList scheduleServiceList){
            textViewFrom.setText(scheduleServiceList.getOrigin());
            textViewTo.setText(scheduleServiceList.getDestination());
            textViewDepartureTime.setText(scheduleServiceList.getDepartureTime());
            textViewArrivalTime.setText(scheduleServiceList.getArrivalTime());
        }
    }
}
