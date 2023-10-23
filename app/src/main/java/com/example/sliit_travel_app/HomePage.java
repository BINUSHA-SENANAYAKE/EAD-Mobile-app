package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sliit_travel_app.databinding.ActivityLoginBinding;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button button_d1 = findViewById(R.id.doc_page1_btn);
        Button button_d2 = findViewById(R.id.doc_page1_btn2);
        ImageView imageView = findViewById(R.id.profileIcon);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Do you want to deactivate this account?";
                showCustomDialogBox(message);
            }
        });

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

    private void showCustomDialogBox(String message) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_profile_deactivat_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tvMessage = dialog.findViewById(R.id.delete_popup_text);
        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        tvMessage.setText(message);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Clicked", "Yes Clicked");
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private String retrieveDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("userData", null);
    }


}