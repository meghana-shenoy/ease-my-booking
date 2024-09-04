package com.example.easemybooking.model;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentID;
    private String mode_payment;
    private Float total_amt;

    @OneToOne(mappedBy = "payment")
    private Booking booking;

    public int getPaymentID() {
        return paymentID;
    }

    public void setpaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getMode_payment() {
        return mode_payment;
    }

    public void setMode_payment(String mode_payment) {
        this.mode_payment = mode_payment;
    }

    public Float getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(Float total_amt) {
        this.total_amt = total_amt;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "pyid='" + paymentID + '\'' +
                ", mode_payment='" + mode_payment + '\'' +
                ", total_amt=" + total_amt +
                ", booking=" + booking +
                '}';
    }
}
