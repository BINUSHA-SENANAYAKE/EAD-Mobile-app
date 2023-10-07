package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selected_reservation_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_reservation_details);

        Button button_d1 = findViewById(R.id.doc_page4_btn1);

        button_d1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(selected_reservation_details.this, Reservation_ammount.class);
                startActivity(intent);
            }
        });
    }
}