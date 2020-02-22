package com.example.geo_reminder;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "reminders", primaryKeys = {"info"})
public class Reminder implements Serializable {

    @ColumnInfo(name = "info")
    @NonNull
    private String info;

    @ColumnInfo(name = "longitude")
    private double longitude;

    @ColumnInfo(name = "Latitude")
    private double latitude;

    @ColumnInfo(name = "radius")
    private int radius;

    public Reminder(String info, double latitude, double longitude, int radius){
        this.info = info;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }



    public String getInfo() {
        return info;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() { return longitude; }

    public int getRadius() {
        return radius;
    }


}
