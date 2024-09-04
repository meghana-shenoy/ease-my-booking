package com.example.easemybooking.controller;

import com.example.easemybooking.model.*;
import com.example.easemybooking.service.BookingService;
import com.example.easemybooking.service.CancellationService;
import com.example.easemybooking.service.DestinationService;
import com.example.easemybooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    CancellationService cancellationService;

    @Autowired
    PaymentService paymentService;


    @PostMapping("/booking/add")
    public Booking addBooking(@RequestBody Booking booking) {
        System.out.println(booking);
        return bookingService.addBooking(booking);
    }

    @GetMapping("/booking/{id}")
    public Booking findbookingbyBookingId(@PathVariable("id") int id) {
        return bookingService.findBookingbyId(id);
    }

    @GetMapping("/customer/{id}/bookings/")
    public List<Booking> findbycid(@PathVariable("id") int id) {
        return bookingService.findBookingsByCid(id);
    }

    @GetMapping("/destination/{id}/bookings")
    public List<Booking> findbydid(@PathVariable("id") int id) {
        return bookingService.findBookingsByDid(id);
    }

    @PostMapping("/booking/{id}/createcancellation")
    public Cancellation addCancellation(@RequestBody Cancellation cancellation) {
        System.out.println(cancellation);
        return cancellationService.addCancellation(cancellation);
    }


    @GetMapping("/booking/{id}/cancellation")
    public Cancellation findcancellation(@PathVariable("id") int id) {
        return cancellationService.findCancellation(id);





    }


}
