package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class selected_reservation_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_reservation_details);

        Intent intent = getIntent();
        int passengerCount = intent.getIntExtra("passengerCount", 0); // 0 is the default value
        String selectedDate = intent.getStringExtra("selectedDate");
        String from = intent.getStringExtra("from");
        String to = intent.getStringExtra("to");

        // Find the TextViews
        TextView passengerCountTextView = findViewById(R.id.doc_page4_text1a);
        TextView selectedDateTextView = findViewById(R.id.doc_page4_text2a);
        TextView fromTextView = findViewById(R.id.doc_page4_text3a2);
        TextView toTextView = findViewById(R.id.doc_page4_text4a);

        // Set the retrieved values to the TextViews
        passengerCountTextView.setText(String.valueOf(passengerCount));
        selectedDateTextView.setText(selectedDate);
        fromTextView.setText(from);
        toTextView.setText(to);


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