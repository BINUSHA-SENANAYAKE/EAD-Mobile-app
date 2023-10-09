package com.example.sliit_travel_app.schedules;

import com.example.sliit_travel_app.scheduleServicesListApiResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiInterfce {

   @GET("schedules")
   Call<scheduleServicesListApiResponse> getServiceList();
}
