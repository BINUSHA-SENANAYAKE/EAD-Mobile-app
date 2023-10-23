package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.sliit_travel_app.schedules.ApiInterfce;
import com.example.sliit_travel_app.schedules.ApiSchedule;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reservation_History extends AppCompatActivity {

    RecyclerView recyclerView;
    AddedHistoryScheduleAdapter addedHistoryScheduleAdapter;
    ArrayList<AddedSchedulesServicesList> addedSchedulesServicesLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_history);

        addedSchedulesServicesLists = new ArrayList<>();
        recyclerView = findViewById(R.id.train_history_reservation_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addedHistoryScheduleAdapter = new AddedHistoryScheduleAdapter(Reservation_History.this , addedSchedulesServicesLists);
        recyclerView.setAdapter(addedHistoryScheduleAdapter);
        populateServices();
    }

    private String retrieveDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userData = sharedPreferences.getString("userData", null);
        Log.d("userData1", userData);
        return userData;
    }

    public void populateServices(){
        String userData = retrieveDataFromSharedPreferences();
        ApiSchedule.getHistorySchedule(userData).create(ApiInterfce.class).getAddedHistoryScheduleLis().enqueue(new Callback<AddedScheduleServicesListApiResponse>() {
            @Override
            public void onResponse(Call<AddedScheduleServicesListApiResponse> call, Response<AddedScheduleServicesListApiResponse> response) {
                if (response.code() == 200) {
                    if (response.body().isSuccess()) {
                        addedSchedulesServicesLists.addAll(response.body().getData());
                        addedHistoryScheduleAdapter.notifyDataSetChanged(); // Correct method to notify the adapter
                    }
                }
            }

            @Override
            public void onFailure(Call<AddedScheduleServicesListApiResponse> call, Throwable t) {

            }
        });
    }
}