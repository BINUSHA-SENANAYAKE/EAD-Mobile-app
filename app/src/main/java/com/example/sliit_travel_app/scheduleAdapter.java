package com.example.sliit_travel_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class scheduleAdapter extends RecyclerView.Adapter<scheduleAdapter.ViewHolder> {

    Context context;
    ArrayList<scheduleServiceList> scheduleServiceList;
    ArrayList<stationService> stationService;

    private OnReserveButtonClickListener onReserveButtonClickListener; // Add this field

    //**************************************************************************************************************************
    public interface OnReserveButtonClickListener {
        void onReserveButtonClick(scheduleServiceList schedule);
    }



    public scheduleAdapter(Context context, ArrayList<scheduleServiceList> arrayList, OnReserveButtonClickListener listener) {
        this.context = context;
        this.scheduleServiceList = arrayList;
        this.onReserveButtonClickListener = listener;
    }
    //**************************************************************************************************************************

    public scheduleAdapter(Context context , ArrayList<scheduleServiceList> arrayList) {
        this.context = context;
        this.scheduleServiceList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_view,parent,false);
        return new ViewHolder(view);
       // return new scheduleAdapter(LayoutInflater.from(context).inflate(R.layout.schedule_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(scheduleServiceList.get(position));
//        holder.reserve_now.setOnClickListener(view -> {
//            if (onReserveButtonClickListener != null) {
//                onReserveButtonClickListener.onReserveButtonClick(scheduleServiceList.get(position));
//            }
//        });
        holder.reserve_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleServiceList item = scheduleServiceList.get(position);
                Log.d("TRAIN","ONclick"+item);
                if (onReserveButtonClickListener != null) {
                    onReserveButtonClickListener.onReserveButtonClick(item);
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return scheduleServiceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewFrom, textViewTo, textViewDepartureTime, textViewArrivalTime;
        private ArrayList<String> stationNamesList; // Create an ArrayList to store station names

        private Button reserve_now;

    //    private RecyclerView relativeLayout3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFrom = itemView.findViewById(R.id.train_from);
            textViewTo = itemView.findViewById(R.id.train_to);
            textViewDepartureTime = itemView.findViewById(R.id.departureTime);
            textViewArrivalTime = itemView.findViewById(R.id.arrivalTime);
            stationNamesList = new ArrayList<>(); // Initialize the ArrayList
            reserve_now = itemView.findViewById(R.id.reserve_now);


        }

        public void bind(scheduleServiceList scheduleServiceList) {
            textViewFrom.setText(scheduleServiceList.getOrigin());
            textViewTo.setText(scheduleServiceList.getDestination());
            textViewDepartureTime.setText(scheduleServiceList.getDepartureTime());
            textViewArrivalTime.setText(scheduleServiceList.getArrivalTime());

            // Add code to process stations data
            ArrayList<stationService> stations = (ArrayList<com.example.sliit_travel_app.stationService>) scheduleServiceList.getStations();
            if (stations != null && !stations.isEmpty()) {
                stationNamesList.clear(); // Clear the previous station names (if any)

                for (stationService station : stations) {
                    stationNamesList.add(station.getName()); // Add station names to the ArrayList
                }
            }


        }

        // Add a getter method to retrieve the station names ArrayList
        public ArrayList<String> getStationNamesList() {
            return stationNamesList;
        }
    }



}
