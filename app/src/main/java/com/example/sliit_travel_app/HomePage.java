package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sliit_travel_app.databinding.ActivityLoginBinding;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button button_d1 = findViewById(R.id.doc_page1_btn);
        Button button_d2 = findViewById(R.id.doc_page1_btn2);

        button_d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, TrainSchedule.class);
                startActivity(intent);
            }
        }
        );
    }
}