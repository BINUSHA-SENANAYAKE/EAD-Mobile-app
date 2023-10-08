package com.example.sliit_travel_app;

import java.util.ArrayList;
import java.util.List;

public class scheduleServicesListApiResponse {
   boolean success;
   String message;
   ArrayList<scheduleServiceList> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<scheduleServiceList> getData() {
        return data;
    }

}
