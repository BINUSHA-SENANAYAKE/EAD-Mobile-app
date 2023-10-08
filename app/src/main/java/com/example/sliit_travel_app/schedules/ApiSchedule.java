package com.example.sliit_travel_app.schedules;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSchedule {

    final static String base_url = "https://app-ticket-ease-api.azurewebsites.net/api/";
    static Retrofit retrofit = null;

   public static Retrofit getSchedule() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
