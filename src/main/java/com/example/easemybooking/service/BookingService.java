package com.example.easemybooking.service;

import com.example.easemybooking.model.Booking;

import java.util.List;

public interface BookingService {
    Booking addBooking(Booking booking);
    Booking findBookingbyId(int id);
    List<Booking> findbyCustomerId(int id);
    List<Booking> findbyDestinationId(int id);
}
