package com.example.easemybooking.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String cfname;
    private String cmidname;
    private String clname;
    private String cemail;
    private String password;
    private String ccphoneno;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;

    public Customer() {
    }

    public Customer(int cid, String cfname, String cmidname, String clname, String cemail, String ccphoneno, String password) {
        this.customerId = cid;
        this.cfname = cfname;
        this.cmidname = cmidname;
        this.clname = clname;
        this.cemail = cemail;
        this.ccphoneno = ccphoneno;
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCfname() {
        return cfname;
    }

    public void setCfname(String cfname) {
        this.cfname = cfname;
    }

    public String getCmidname() {
        return cmidname;
    }

    public void setCmidname(String cmidname) {
        this.cmidname = cmidname;
    }

    public String getClname() {
        return clname;
    }

    public void setClname(String clname) {
        this.clname = clname;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getCcphoneno() {
        return ccphoneno;
    }

    public void setCcphoneno(String ccphoneno) {
        this.ccphoneno = ccphoneno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer_details{" +
                "cid='" + customerId + '\'' +
                ", cfname='" + cfname + '\'' +
                ", cmidname='" + cmidname + '\'' +
                ", clname='" + clname + '\'' +
                ", cemail='" + cemail + '\'' +
                ", ccphoneno='" + ccphoneno + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
