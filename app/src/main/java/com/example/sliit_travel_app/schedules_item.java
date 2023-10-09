package com.example.sliit_travel_app;

public class schedules_item {
    String From;
    String to;
    String departure;
    String arrival;
    Number id;

    public schedules_item(String from, String to, String departure, String arrival, Number id) {
        From = from;
        this.to = to;
        this.departure = departure;
        this.arrival = arrival;
        this.id = id;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }
}
