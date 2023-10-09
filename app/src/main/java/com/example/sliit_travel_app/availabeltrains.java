package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sliit_travel_app.schedules.ApiInterfce;
import com.example.sliit_travel_app.schedules.ApiSchedule;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class availabeltrains extends AppCompatActivity implements scheduleAdapter.OnReserveButtonClickListener{

    RecyclerView recyclerview;
    scheduleAdapter scheduleAdapter;
    ArrayList<scheduleServiceList> scheduleServiceLists;
    private Button reserve_now;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availabeltrains);

        setContentView(R.id.reserve_now);

        scheduleServiceLists = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        scheduleAdapter = new scheduleAdapter(availabeltrains.this,scheduleServiceLists );
        recyclerview.setAdapter(scheduleAdapter);
        populateServices();

        reserve_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ButtonClicked", "Reserve Now button clicked");
                // When the button is clicked, start the TrainReservePage activity
                Intent intent = new Intent(availabeltrains.this, TrainReservePage.class);
                startActivity(intent);
            }
        });

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

    public void onReserveButtonClick(scheduleServiceList schedule) {
        // Handle the button click event here
        Intent intent = new Intent(availabeltrains.this, TrainReservePage.class);
        intent.putExtra("selected_schedule", schedule); // Pass the selected schedule data
        startActivity(intent);
    }
}