package com.example.sliit_travel_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.sliit_travel_app.schedules.ApiInterfce;
import com.example.sliit_travel_app.schedules.ApiSchedule;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class train_all_reservations extends AppCompatActivity implements AddedScheduleServiceAdapter.OnCancelButtonClickListner {

    RecyclerView recyclerView;
    private Button cancel_res;

    AddedScheduleServiceAdapter addedScheduleServiceAdapter;
    ArrayList<AddedSchedulesServicesList> addedSchedulesServicesLists;

    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_all_reservations);

        addedSchedulesServicesLists = new ArrayList<>();
        recyclerView = findViewById(R.id.train_reservation_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addedScheduleServiceAdapter = new AddedScheduleServiceAdapter(train_all_reservations.this, addedSchedulesServicesLists, this);
        recyclerView.setAdapter(addedScheduleServiceAdapter);
        populateServices();

        cancel_res = findViewById(R.id.button_c);
        if (cancel_res != null) {
            cancel_res.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ButtonClicked1", "Reserve Now button clicked");
                    Intent intent = new Intent(train_all_reservations.this, Cancel_reservation_popup.class);
                    startActivity(intent);
                }
            });
        }
    }

    public void populateServices() {
        String userData = retrieveDataFromSharedPreferences();
        ApiSchedule.getAddedSchedule(userData).create(ApiInterfce.class).getAddedScheduleLis().enqueue(new Callback<AddedScheduleServicesListApiResponse>() {
            @Override
            public void onResponse(Call<AddedScheduleServicesListApiResponse> call, Response<AddedScheduleServicesListApiResponse> response) {
                if (response.code() == 200) {
                    if (response.body().isSuccess()) {
                        addedSchedulesServicesLists.addAll(response.body().getData());
                        addedScheduleServiceAdapter.notifyDataSetChanged(); // Correct method to notify the adapter
                    }
                }
            }
            @Override
            public void onFailure(Call<AddedScheduleServicesListApiResponse> call, Throwable t) {

            }
        });

    }

    private String retrieveDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userData = sharedPreferences.getString("userData", null);
        Log.d("userData1", userData);
        return userData;
    }

    @Override
    public void onCancelButtonClick(AddedSchedulesServicesList addedSchedule) {
        String message = "Are you Sure ?";
        showCustomDialogBox(message, addedSchedule);
    }

    private void showCustomDialogBox(String message, AddedSchedulesServicesList addedSchedule) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_cancel_reservation_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tvMessage = dialog.findViewById(R.id.delete_popup_text);
        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        tvMessage.setText(message);

        String reservationId =  addedSchedule.id;
        Log.d("reservationId", reservationId);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Clicked", "Yes Clicked");
                String userData = retrieveDataFromSharedPreferences();
                deleteDataUsingVolley(reservationId, userData, addedSchedule);
                dialog.dismiss();
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void deleteDataUsingVolley(String reservationId, String userData,AddedSchedulesServicesList addedSchedule) {
        // Define the base URL with query parameters
        String baseUrl = "https://app-ticket-ease-api.azurewebsites.net/api/reservations?reservationId=" + reservationId;
        Log.d("DeleteRequest", baseUrl);
        Log.d("DeleteRequest", userData);

        RequestQueue queue = Volley.newRequestQueue(train_all_reservations.this);

        com.android.volley.toolbox.StringRequest request = new com.android.volley.toolbox.StringRequest(Request.Method.DELETE, baseUrl,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DeleteRequest", response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                // Remove the deleted reservation from the list
                                addedSchedulesServicesLists.remove(addedSchedule);
                                // Notify the adapter to refresh the RecyclerView
                                addedScheduleServiceAdapter.notifyDataSetChanged();
                                Toast.makeText(train_all_reservations.this, "Reservation deleted successfully.", Toast.LENGTH_SHORT).show();
                            } else {
                                String message = jsonResponse.getString("message");
                                Toast.makeText(train_all_reservations.this, "Delete failed: " + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(train_all_reservations.this, "Delete failed: Invalid response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(train_all_reservations.this, "Delete failed : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + userData);
                return headers;
            }
        };

        queue.add(request);
    }




}