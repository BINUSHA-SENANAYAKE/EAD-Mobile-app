package com.example.sliit_travel_app;

import androidx.appcompat.app.ActionBar;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.sliit_travel_app.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button button_d1 = findViewById(R.id.doc_page1_btn);
        Button button_d2 = findViewById(R.id.doc_page1_btn2);
        ImageView imageView = findViewById(R.id.profileIcon);



        String userData = retrieveDataFromSharedPreferences();
        Log.d("UserData3", userData);

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
                deactivateUser();
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

    private void deactivateUser() {
        // Define the base URL with query parameters
        String baseUrl = "https://app-ticket-ease-api.azurewebsites.net/api/users/deactivate";
        Log.d("deactivateUserA", baseUrl);

        RequestQueue queue = Volley.newRequestQueue(HomePage.this);

        com.android.volley.toolbox.StringRequest request = new com.android.volley.toolbox.StringRequest(Request.Method.POST, baseUrl,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response here
                        Log.d("deactivateUserB", response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                // Reservation was deleted successfully
                                Toast.makeText(HomePage.this, "User deactivated successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(HomePage.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                // Handle other cases where deletion might not be successful
                                String message = jsonResponse.getString("message");
                                Toast.makeText(HomePage.this, "Deactivated failed: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Handle the case where the response is not valid JSON
                            Toast.makeText(HomePage.this, "Deactivated failed: Invalid response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log the error message and stack trace for debugging
                        Log.e("deactivateUser1", "Delete failed: " + error.getMessage());
                        error.printStackTrace();

                        // Display a user-friendly error message
                        Toast.makeText(HomePage.this, "Delete failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            String userData = retrieveDataFromSharedPreferences();

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();

                headers.put("Authorization", "Bearer " + userData);

                // Log the headers for debugging
                for (String key : headers.keySet()) {
                    Log.d("Header", key + ": " + headers.get(key));
                }

                return headers;
            }
        };

        // Log the entire request including URL and headers
        Log.d("VolleyRequest", "Full Request: " + request.getUrl());

        // Add the request to the Volley queue
        queue.add(request);
    }




}