package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sliit_travel_app.schedules.ApiInterfce;
import com.example.sliit_travel_app.schedules.ApiSchedule;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class availabeltrains extends AppCompatActivity {

    RecyclerView recyclerview;
    scheduleAdapter scheduleAdapter;
    ArrayList<scheduleServiceList> scheduleServiceLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availabeltrains);

        scheduleServiceLists = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        scheduleAdapter = new scheduleAdapter(availabeltrains.this,scheduleServiceLists );
        recyclerview.setAdapter(scheduleAdapter);
        populateServices();
    }

    public void populateServices(){
        ApiSchedule.getSchedule().create(ApiInterfce.class).getServiceList().enqueue(new Callback<scheduleServicesListApiResponse>() {
            @Override
            public void onResponse(Call<scheduleServicesListApiResponse> call, Response<scheduleServicesListApiResponse> response) {
                if (response.code() == 200) {
                    if (response.body().isSuccess()) {
                        scheduleServiceLists.addAll(response.body().getData());
                        scheduleAdapter.notifyDataSetChanged(); // Correct method to notify the adapter
                    }
                }
            }


            @Override
            public void onFailure(Call<scheduleServicesListApiResponse> call, Throwable t) {

            }
        });
    }
}