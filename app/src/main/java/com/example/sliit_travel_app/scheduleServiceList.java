package com.example.sliit_travel_app;

import java.util.ArrayList;
import java.util.List;

public class scheduleServiceList {
    String id;
    String name;
    String origin;
    String destination;
    String departureTime;
    String arrivalTime;

    trainNameService train;
    private ArrayList<stationService> stations;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public trainNameService getTrain() {
        return train;
    }

    public List<stationService> getStations() {
        return stations;
    }
}
