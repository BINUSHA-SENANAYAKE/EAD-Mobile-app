package com.example.sliit_travel_app;

public class stationService {
    private String name;
    private String city;
    private String province;
    private String previousStation;
    private String nextStation;
    private String distanceToNextStation;
    private String distanceToPreviousStation;
    private String id;
    private String createdAt;
    private String modifiedAt;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPreviousStation() {
        return previousStation;
    }

    public String getNextStation() {
        return nextStation;
    }

    public String getDistanceToNextStation() {
        return distanceToNextStation;
    }

    public String getDistanceToPreviousStation() {
        return distanceToPreviousStation;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }
}
