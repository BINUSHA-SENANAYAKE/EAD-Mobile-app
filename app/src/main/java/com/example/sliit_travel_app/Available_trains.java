package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sliit_travel_app.R.id;

import java.util.ArrayList;
import java.util.List;

public class Available_trains extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_trains);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        List<schedules_item> items = new ArrayList<schedules_item>();
        items.add(new schedules_item("Ratmalana" , "Kandy" , "12.00" , "6.00" , 10));
        items.add(new schedules_item("Ratmalana1" , "Kandy" , "12.00" , "6.00" , 10));
        items.add(new schedules_item("Ratmalana2" , "Kandy" , "12.00" , "6.00" , 10));
        items.add(new schedules_item("Ratmalana3" , "Kandy" , "12.00" , "6.00" , 10));
        items.add(new schedules_item("Ratmalana4" , "Kandy" , "12.00" , "6.00" , 10));
        items.add(new schedules_item("Ratmalana5" , "Kandy" , "12.00" , "6.00" , 10));
        items.add(new schedules_item("Ratmalana6" , "Kandy" , "12.00" , "6.00" , 10));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new scheduleAdapter(getApplicationContext(),items));
    }
}