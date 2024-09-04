package com.example.easemybooking.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Cancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private Date cancellation_date;
    private float refund_amt;

    @OneToOne(mappedBy = "cancellation")
    private Booking booking;

    public Cancellation(int cid, Date cancellation_date, float refund_amt) {
        this.cid = cid;
        this.cancellation_date = cancellation_date;
        this.refund_amt = refund_amt;
    }

    public Cancellation(int cid, Date cancellation_date, float refund_amt, Booking booking) {
        this.cid = cid;
        this.cancellation_date = cancellation_date;
        this.refund_amt = refund_amt;
        this.booking = booking;
    }

    public int getBid() {
        return cid;
    }

    public void setBid(int bid) {
        this.cid = bid;
    }

    public Date getCancellation_date() {
        return cancellation_date;
    }

    public void setCancellation_date(Date cancellation_date) {
        this.cancellation_date = cancellation_date;
    }

    public float getRefund_amt() {
        return refund_amt;
    }

    public void setRefund_amt(float refund_amt) {
        this.refund_amt = refund_amt;
    }

    @Override
    public String toString() {
        return "Cancellation{" +
                "cid='" + cid + '\'' +
                ", cancellation_date=" + cancellation_date +
                ", refund_amt=" + refund_amt +
                '}';
    }
}


