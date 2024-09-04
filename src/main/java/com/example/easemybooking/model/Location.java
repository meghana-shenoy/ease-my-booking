package com.example.easemybooking.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lid;
    private String lname;

    public Location() {
    }

    public Location(int lid, String lname) {
        this.lid = lid;
        this.lname = lname;
    }

    @OneToMany(mappedBy = "location")
    private List<Destination> destination;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lid='" + lid + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}
