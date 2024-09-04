package com.example.easemybooking.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Cancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cancelSequence")
    @SequenceGenerator(name = "cancelSequence" , allocationSize = 0)
    private int cancellationId;

    private Date cancellation_date;
    private float refund_amt;

    @OneToOne()
    @JoinColumn(name = "bookingId")
    private Booking booking;

    public Cancellation(int cancellationId, Date cancellation_date, float refund_amt) {
        this.cancellationId = cancellationId;
        this.cancellation_date = cancellation_date;
        this.refund_amt = refund_amt;
    }

    public Cancellation(int cancellationId, Date cancellation_date, float refund_amt, Booking booking) {
        this.cancellationId = cancellationId;
        this.cancellation_date = cancellation_date;
        this.refund_amt = refund_amt;
        this.booking = booking;
    }

    public Cancellation() {

    }

    public int getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(int cancellationId) {
        this.cancellationId = cancellationId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
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
                "cid='" + cancellationId + '\'' +
                ", cancellation_date=" + cancellation_date +
                ", refund_amt=" + refund_amt +
                '}';
    }
}