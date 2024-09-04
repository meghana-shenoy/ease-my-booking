package com.example.easemybooking.model;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentSequence")
    @SequenceGenerator(name = "paymentSequence" , allocationSize = 0)
    private int paymentID;

    @Enumerated(EnumType.STRING)
    private PaymentType mode_payment;

    private Float total_amt;

    @OneToOne(mappedBy = "payment")
    private Booking booking;

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public PaymentType getMode_payment() {
        return mode_payment;
    }

    public void setMode_payment(PaymentType mode_payment) {
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
