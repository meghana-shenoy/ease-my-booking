package com.example.easemybooking.model;

import javax.persistence.*;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ofid;
    private Integer summeroff;
    private Integer winteroff;
    private Integer rainoff;
    private Integer tot_reduction;

    @OneToOne(mappedBy = "offer")
    private Booking booking;

    public int getOfid() {
        return ofid;
    }

    public void setOfid(int ofid) {
        this.ofid = ofid;
    }

    public Integer getSummeroff() {
        return summeroff;
    }

    public void setSummeroff(Integer summeroff) {
        this.summeroff = summeroff;
    }

    public Integer getWinteroff() {
        return winteroff;
    }

    public void setWinteroff(Integer winteroff) {
        this.winteroff = winteroff;
    }

    public Integer getRainoff() {
        return rainoff;
    }

    public void setRainoff(Integer rainoff) {
        this.rainoff = rainoff;
    }

    public Integer getTot_reduction() {
        return tot_reduction;
    }

    public void setTot_reduction(Integer tot_reduction) {
        this.tot_reduction = tot_reduction;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Offer_details{" +
                "ofid='" + ofid + '\'' +
                ", summeroff=" + summeroff +
                ", winteroff=" + winteroff +
                ", rainoff=" + rainoff +
                ", tot_reduction=" + tot_reduction +
                ", booking=" + booking +
                '}';
    }
}
