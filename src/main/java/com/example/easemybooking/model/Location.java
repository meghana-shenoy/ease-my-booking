package com.example.easemybooking.model;

import javax.persistence.*;
import java.util.List;
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locationSequence")
    @SequenceGenerator(name = "locationSequence" , allocationSize = 0)
    private int locationId;
    private String locationName;


    public Location(int locationId, String lname) {
        this.locationId = locationId;
        this.locationName = lname;
    }

    public Location() {

    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int lid) {
        this.locationId = lid;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String lname) {
        this.locationName = lname;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lid='" + locationId + '\'' +
                ", lname='" + locationName + '\'' +
                '}';
    }
}