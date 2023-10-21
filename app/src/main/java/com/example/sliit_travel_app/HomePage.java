package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
                Intent intent = new Intent(HomePage.this, availabeltrains.class);
                startActivity(intent);
            }
        }
        );
        button_d2.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent intent = new Intent(HomePage.this, train_all_reservations.class);
                                             startActivity(intent);
                                         }
                                     }
        );

        String userData = retrieveDataFromSharedPreferences();

        if (userData != null) {
            Log.d("UserData", userData); // Log the userData
            // Now you can use the userData as needed in this part of your code
        } else {
            Log.d("UserData", "Data not found in SharedPreferences"); // Log a message indicating data was not found
            // Handle the case where data is not found in SharedPreferences
        }
    }

    private String retrieveDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("userData", null);
    }


}