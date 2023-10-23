package com.example.sliit_travel_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddedHistoryScheduleAdapter extends RecyclerView.Adapter<AddedHistoryScheduleAdapter.ViewHolder>{

    Context context;
    ArrayList<AddedSchedulesServicesList> addedSchedulesServicesLists;

    public AddedHistoryScheduleAdapter(Context context , ArrayList<AddedSchedulesServicesList> arrayList) {
        this.context = context;
        this.addedSchedulesServicesLists = arrayList;
    }

    @NonNull
    @Override
    public AddedHistoryScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_train_history_single_reservation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddedHistoryScheduleAdapter.ViewHolder holder, int position) {
        holder.bind(addedSchedulesServicesLists.get(position));
    }

    @Override
    public int getItemCount() {
        return addedSchedulesServicesLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id , userId ,  scheduleId , fromStationId , toStationId , date , passengerCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                fromStationId = itemView.findViewById(R.id.from_b);
                toStationId = itemView.findViewById(R.id.train_to_b);
                date = itemView.findViewById(R.id.date_b);
                passengerCount = itemView.findViewById(R.id.passengers_b);
                Log.d("passengerCount1", String.valueOf(passengerCount));
            }
        public void bind(AddedSchedulesServicesList addedSchedulesServicesList){
            fromStationId.setText(addedSchedulesServicesList.getFromStationId());
            toStationId.setText(addedSchedulesServicesList.getToStationId());
            date.setText(addedSchedulesServicesList.getDate());
            passengerCount.setText(String.valueOf(addedSchedulesServicesList.getPassengerCount()));
        }
    }
}
