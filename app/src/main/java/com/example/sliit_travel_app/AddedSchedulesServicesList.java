package com.example.sliit_travel_app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddedSchedulesServicesList implements Serializable{
    String id;
    String userId;
    String scheduleId;
    String fromStationId;
    String toStationId;
    String date;
    int passengerCount;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getFromStationId() {
        return fromStationId;
    }

    public String getToStationId() {
        return toStationId;
    }

    public String getDate() {
        return date;
    }

    public int getPassengerCount() {
        return passengerCount;
    }
}
