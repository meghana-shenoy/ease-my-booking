package com.example.easemybooking.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Owners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ownerid;
    private String ophone;
    private String ofname;
    private String omname;
    private String olname;

    @OneToMany(mappedBy = "owner")
    private List<Destination> destination;

    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int oid) {
        this.ownerid = oid;
    }

    public String getOphone() {
        return ophone;
    }

    public void setOphone(String ophone) {
        this.ophone = ophone;
    }

    public String getOfname() {
        return ofname;
    }

    public void setOfname(String ofname) {
        this.ofname = ofname;
    }

    public String getOmname() {
        return omname;
    }

    public void setOmname(String omname) {
        this.omname = omname;
    }

    public String getOlname() {
        return olname;
    }

    public void setOlname(String olname) {
        this.olname = olname;
    }

    @Override
    public String toString() {
        return "Owners{" +
                "oid='" + ownerid + '\'' +
                ", ophone='" + ophone + '\'' +
                ", ofname='" + ofname + '\'' +
                ", omname='" + omname + '\'' +
                ", olname='" + olname + '\'' +
                '}';
    }
}
