package com.example.easemybooking.service;

import com.example.easemybooking.model.Booking;
import com.example.easemybooking.model.Destination;
import com.example.easemybooking.repo.BookingRepo;
import com.example.easemybooking.repo.DestinationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    BookingRepo bookingRepo;


    @Override
    public List<Booking> findbyCustomerId(int id) {
        return bookingRepo.findByUser_UserId(id);
    }

    @Override
    public List<Booking> findbyDestinationId(int id) {
        return bookingRepo.findByDestination_DestinationId(id);
    }


    @Transactional
    @Override
    public Booking addBooking(Booking booking) {
        bookingRepo.save(booking);
        System.out.println("booking after saving"  +booking);
        return booking;
    }

    @Override
    public Booking findBookingbyId(int id) {
        System.out.println("hi");
        return bookingRepo.findById(id).orElse(null);
    }

}
