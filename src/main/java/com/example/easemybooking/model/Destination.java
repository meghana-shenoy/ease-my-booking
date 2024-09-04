package com.example.easemybooking.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int destinationId;
    private Integer adultfee;
    private Integer childfee;
    private String destinationName;
    private String destinationAddress;
    private String destinationPhoneno;


    @OneToMany(mappedBy = "destination")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name="lid")
    private Location location;

    @ManyToOne
    @JoinColumn(name="ownerid")
    private Owners owner;

    public Destination() {
    }

    public Destination(int did, Integer adultfee, Integer childfee, String dname, String daddress, String dphoneno, Location location, Owners owner) {
        this.destinationId = did;
        this.adultfee = adultfee;
        this.childfee = childfee;
        this.destinationName = dname;
        this.destinationAddress = daddress;
        this.destinationPhoneno = dphoneno;
        this.location = location;
        this.owner = owner;
    }


    public int getDid() {
        return destinationId;
    }

    public void setDid(int did) {
        this.destinationId = did;
    }

    public Integer getAdultfee() {
        return adultfee;
    }

    public void setAdultfee(Integer adultfee) {
        this.adultfee = adultfee;
    }

    public Integer getChildfee() {
        return childfee;
    }

    public void setChildfee(Integer childfee) {
        this.childfee = childfee;
    }

    public String getDname() {
        return destinationName;
    }

    public void setDname(String dname) {
        this.destinationName = dname;
    }

    public String getDaddress() {
        return destinationAddress;
    }

    public void setDaddress(String daddress) {
        this.destinationAddress = daddress;
    }

    public String getDphoneno() {
        return destinationPhoneno;
    }

    public void setDphoneno(String dphoneno) {
        this.destinationPhoneno = dphoneno;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Owners getOwner() {
        return owner;
    }

    public void setOwner(Owners owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "did='" + destinationId + '\'' +
                ", adultfee=" + adultfee +
                ", childfee=" + childfee +
                ", dname='" + destinationName + '\'' +
                ", daddress='" + destinationAddress + '\'' +
                ", dphoneno='" + destinationPhoneno + '\'' +
                ", location=" + location +
                ", owner=" + owner +
                '}';
    }
}
