package com.example.sliit_travel_app;

public class stationService {
     String name;
     String city;
     String province;
     String previousStation;
     String nextStation;
     String distanceToNextStation;
     String distanceToPreviousStation;
     String id;
     String createdAt;
     String modifiedAt;

     String getName() {
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
