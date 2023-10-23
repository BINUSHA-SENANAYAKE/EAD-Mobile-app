package com.example.sliit_travel_app;

import java.util.ArrayList;

public class AddedScheduleServicesListApiResponse {

    boolean success;
    String message;
    ArrayList<AddedSchedulesServicesList> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<AddedSchedulesServicesList> getData() {
        return data;
    }
}
