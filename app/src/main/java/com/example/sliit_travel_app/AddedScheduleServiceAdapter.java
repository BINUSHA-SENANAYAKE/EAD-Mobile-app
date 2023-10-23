package com.example.sliit_travel_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddedScheduleServiceAdapter extends RecyclerView.Adapter<AddedScheduleServiceAdapter.ViewHolder> {

    Context context;
    ArrayList<AddedSchedulesServicesList> addedSchedulesServicesLists;


    //***************************************************************************************
    private OnCancelButtonClickListner onCancelButtonClickListner;

    public interface OnCancelButtonClickListner{
        void onCancelButtonClick(AddedSchedulesServicesList addedSchedule);
    }

    //***************************************************************************************

    public AddedScheduleServiceAdapter(Context context , ArrayList<AddedSchedulesServicesList> arrayList ,OnCancelButtonClickListner listener ) {
        this.context = context;
        this.addedSchedulesServicesLists = arrayList;
        this.onCancelButtonClickListner = listener;
    }

    @NonNull
    @Override
    public AddedScheduleServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_train_single_reservation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddedScheduleServiceAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(addedSchedulesServicesLists.get(position));

        holder.cancel_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddedSchedulesServicesList item =  addedSchedulesServicesLists.get(position);
                if (onCancelButtonClickListner != null){
                    onCancelButtonClickListner.onCancelButtonClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return addedSchedulesServicesLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Button cancel_res;

        private TextView id , userId ,  scheduleId , fromStationId , toStationId , date , passengerCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fromStationId = itemView.findViewById(R.id.from_a);
            toStationId = itemView.findViewById(R.id.train_to_A);
            date = itemView.findViewById(R.id.date_a);
            passengerCount = itemView.findViewById(R.id.passengers_a);
            cancel_res = itemView.findViewById(R.id.button_c);
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
