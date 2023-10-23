package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;

public class Cancel_reservation_popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            Serializable data = intent.getSerializableExtra("selected_schedule");
            Log.d("userData123", data.toString());
            if (data instanceof AddedSchedulesServicesList) {
                AddedSchedulesServicesList selectedSchedule = (AddedSchedulesServicesList) data;
                // Now you can work with selectedSchedule
            }else {
                Log.d("userData132", "Data is null");
            }
        }
    }
}


