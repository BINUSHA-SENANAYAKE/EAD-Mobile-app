package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class selected_reservation_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_reservation_details);

        Intent intent = getIntent();
        int passengerCount = intent.getIntExtra("passengerCount", 1);
        String selectedDate = intent.getStringExtra("selectedDate");
        String from = intent.getStringExtra("from");
        String to = intent.getStringExtra("to");
        String scheduleId = intent.getStringExtra("scheduleId");

        Log.d("Date", String.valueOf(selectedDate));
        if (scheduleId != null) {
            Log.d("scheduleId", scheduleId);
        } else {
            Log.d("scheduleId", "Data not found in SharedPreferences");

        }

        String hToStation = "65218c7e523f498a621dbba5";
        String hFromStation = "65218cd46445b54bf9e7bc5c";

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
                if (passengerCountTextView.getText().toString().isEmpty() || selectedDateTextView.getText().toString().isEmpty() ||
                        fromTextView.getText().toString().isEmpty() || toTextView.getText().toString().isEmpty()  ) {
                    Toast.makeText(selected_reservation_details.this, "Please enter all values", Toast.LENGTH_SHORT).show();
                } else {
                    String userData = retrieveDataFromSharedPreferences();
                    String convertedDate = convertDateToAPIFormat(selectedDate);
                    Log.d("selectedDate 1", String.valueOf(selectedDate));
                    if (convertedDate != null) {
                        postDataUsingVolley(passengerCountTextView.getText().toString(), convertedDate, scheduleId, hToStation, hFromStation, userData);
                    } else {
                        // Handle the case where date conversion failed
                        Toast.makeText(selected_reservation_details.this, "Date conversion failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        String userData = retrieveDataFromSharedPreferences();

        if (userData != null) {
            Log.d("UserData", userData);
        } else {
            Log.d("UserData", "Data not found in SharedPreferences");
        }

    }

    private String convertDateToAPIFormat(String selectedDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
            Log.d("selectedDate", String.valueOf(selectedDate));
            Date date = inputFormat.parse(selectedDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }




    private String retrieveDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userData = sharedPreferences.getString("userData", null);
        Log.d("userData1", userData);
        return userData;
    }


    private void postDataUsingVolley(String passengerCountTextView, String selectedDateTextView, String scheduleId, String hToStation , String hFromStation ,String userData) {
        String url = "https://app-ticket-ease-api.azurewebsites.net/api/reservations";

        RequestQueue queue = Volley.newRequestQueue(selected_reservation_details.this);


        // Create a JSON object to hold the data
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("scheduleId", scheduleId);
            jsonParams.put("fromStationId", hFromStation);
            jsonParams.put("toStationId", hToStation);
            jsonParams.put("date", selectedDateTextView);
            jsonParams.put("passengerCount", passengerCountTextView);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("jsonParams", String.valueOf(jsonParams));
        final String requestBody = jsonParams.toString();

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respObj = new JSONObject(response);
                            Log.d("respObj", String.valueOf(respObj));
                            boolean success = respObj.getBoolean("success");
                            if (success) {
                                String message = respObj.getString("message");
                                Toast.makeText(selected_reservation_details.this, message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(selected_reservation_details.this, Reservation_ammount.class);
                                startActivity(intent);
                            } else {
                                // Registration failed
                                String errorMessage = respObj.getString("message");
                                Toast.makeText(selected_reservation_details.this, "Reservation failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(selected_reservation_details.this, "Reservation failed due to a server error", Toast.LENGTH_SHORT).show();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error here
                        Toast.makeText(selected_reservation_details.this, "Reservation failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return requestBody.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + userData);
                return headers;
            }

        };


        // Add the request to the Volley queue
        queue.add(request);
    }
}

//    Intent intent = new Intent(selected_reservation_details.this, Reservation_ammount.class);
//    startActivity(intent);