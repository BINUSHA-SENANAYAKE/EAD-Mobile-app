package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class selected_reservation_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_reservation_details);

        Intent intent = getIntent();
        int passengerCount = intent.getIntExtra("passengerCount", 1); // 0 is the default value
        String selectedDate = intent.getStringExtra("selectedDate");
        String from = intent.getStringExtra("from");
        String to = intent.getStringExtra("to");
        String scheduleId = intent.getStringExtra("scheduleId");

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
                    postDataUsingVolley(passengerCountTextView.getText().toString(), selectedDateTextView.getText().toString()
                            ,scheduleId , hToStation , hFromStation);
                }
            }
        });


    }

    private void postDataUsingVolley(String passengerCountTextView, String selectedDateTextView, String hToStation, String scheduleId , String hFromStation) {
        String url = "https://app-ticket-ease-api.azurewebsites.net/api/schedules";

        RequestQueue queue = Volley.newRequestQueue(selected_reservation_details.this);

        // Create a JSON object to hold the data
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("scheduleId", scheduleId); // Set scheduleId
            jsonParams.put("fromStationId", hFromStation); // Set hFromStation
            jsonParams.put("toStationId", hToStation); // Set hToStation
            jsonParams.put("date", selectedDateTextView);
            jsonParams.put("passengerCount", passengerCountTextView);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String requestBody = jsonParams.toString();

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respObj = new JSONObject(response);
                            boolean success = respObj.getBoolean("success");
                            if (success) {
                                // Registration was successful
                                String message = respObj.getString("message");
                                Toast.makeText(selected_reservation_details.this, message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(selected_reservation_details.this, Reservation_ammount.class);
                                startActivity(intent);
                                // Now you can choose to navigate to the login activity
                                // or perform any other action after a successful registration
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
        };

        // Add the request to the Volley queue
        queue.add(request);
    }
}

//    Intent intent = new Intent(selected_reservation_details.this, Reservation_ammount.class);
//    startActivity(intent);