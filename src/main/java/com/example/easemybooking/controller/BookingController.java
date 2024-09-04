package com.example.easemybooking.controller;

import com.example.easemybooking.exception.InvalidTokenException;
import com.example.easemybooking.exception.PaymentFailedException;
import com.example.easemybooking.model.*;
import com.example.easemybooking.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    BookingService bookingService;
    @Autowired
    UserService userService;
    @Autowired
    CancellationService cancellationService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    DestinationService destinationService;

    @PostMapping("/addBooking")
    public Booking addBooking(@RequestBody Booking booking, @RequestParam String username) {
        logger.info("Creating the Booking for user :" + username);
        if (username != null && !username.isEmpty()) {
            User user = userService.findByUsername(username);
            booking.setCurrentdate(new Date());
            booking.setUser(user);
            booking.setBookingdate(new Date());
            booking.setUpdatedate(null);
            Destination destination = destinationService.findByDestinationId(booking.getDestination().getDid());
            booking.setDestination(destination);
            Float totalTicketCost = (float) (destination.getAdultfee() * booking.getAdult_count() +
                    destination.getChildfee() * booking.getChildren_count());
            booking.setVisit_completed(false);
            Payment payment = booking.getPayment();
            if (payment != null) {
                logger.info("Payment initiated for user :" + username);
                payment.setTotal_amt(totalTicketCost);
                logger.info("Total amount to repay is : " + totalTicketCost);
                payment = paymentService.addPayment(payment);
                logger.info("Payment completed for user :" + username);
            }else{
                throw new PaymentFailedException("Payment details are required");
            }
            booking.setPayment(payment);
            return bookingService.addBooking(booking);
//            Booking bookingNew = bookingService.addBooking(booking);
//            payment.setBooking(bookingNew);
//            paymentService.updatePayment(payment);
//            return bookingNew;
        } else {
            throw new InvalidTokenException("Username cannot be empty");
        }
    }

    @GetMapping("/booking/{id}")
    public Booking findbookingbyBookingId(@PathVariable("id") int id) {
        return bookingService.findBookingbyId(id);
    }

    @GetMapping("/customer/{id}/bookings/")
    public List<Booking> findbyCustomerId(@PathVariable("id") int id) {
        return bookingService.findbyCustomerId(id);
    }


    @GetMapping("/destination/{id}/bookings")
    public List<Booking> findbyDestinationId(@PathVariable("id") int id) {
        return bookingService.findbyDestinationId(id);
    }

    @PostMapping("/booking/{id}/cancel")
    public Cancellation cancelBooking(@PathVariable int id, @RequestParam String username) {
        logger.info("Cancelling Booking for user :" + username);
        if (username != null && !username.isEmpty()) {
            Cancellation cancellation = new Cancellation();
            User user = userService.findByUsername(username);
            Booking booking = bookingService.findBookingbyId(id);
            cancellation.setBooking(booking);
            cancellation.setCancellation_date(new Date());
            Payment payment = paymentService.findById(booking.getPayment().getPaymentID());
            cancellation.setRefund_amt(payment.getTotal_amt());
            logger.info("Cancellation completed for user :" + username);
            return cancellationService.addCancellation(cancellation);
        }
        else {
            throw new InvalidTokenException("Cancellation failed!!! ");
        }
    }

    @GetMapping("/booking/{id}/cancellation")
    public Cancellation findcancellation(@PathVariable("id") int id) {
        return cancellationService.findCancellation(id);
    }


}