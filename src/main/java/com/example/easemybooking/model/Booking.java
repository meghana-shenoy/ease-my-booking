package com.example.easemybooking.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingID;
    private Date currentdate;
    private Date bookingdate;
    private Date updatedate;
    private Integer children_count;
    private Integer adult_count;
    private Integer total_fee;
    private boolean visit_completed = false;


    @ManyToOne
    @JoinColumn(name="destinationId")
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "destinationId")
//    @JsonIdentityReference(alwaysAsId = true)
    private Destination destination;

    @ManyToOne
    @JoinColumn(name="customerId")
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
//    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @OneToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "cancellationId")
    private Cancellation cancellation;


    public Booking(int bookingID, Date currentdate, Date bookingdate, Date updatedate, Integer children_count, Integer adult_count, Integer total_fee, boolean visit_completed, Destination destination, User customerDetails, Payment payment, Cancellation cancellation) {
        this.bookingID = bookingID;
        this.currentdate = currentdate;
        this.bookingdate = bookingdate;
        this.updatedate = updatedate;
        this.children_count = children_count;
        this.adult_count = adult_count;
        this.total_fee = total_fee;
        this.visit_completed = visit_completed;
        this.destination = destination;
        this.user = customerDetails;
        this.payment = payment;
        this.cancellation = cancellation;
    }

    public Booking() {

    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Cancellation getCancellation() {
        return cancellation;
    }

    public void setCancellation(Cancellation cancellation) {
        this.cancellation = cancellation;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Date getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(Date currentdate) {
        this.currentdate = currentdate;
    }

    public Date getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(Date bookingdate) {
        this.bookingdate = bookingdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getChildren_count() {
        return children_count;
    }

    public void setChildren_count(Integer children_count) {
        this.children_count = children_count;
    }

    public Integer getAdult_count() {
        return adult_count;
    }

    public void setAdult_count(Integer adult_count) {
        this.adult_count = adult_count;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public boolean isVisit_completed() {
        return visit_completed;
    }

    public void setVisit_completed(boolean visit_completed) {
        this.visit_completed = visit_completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
