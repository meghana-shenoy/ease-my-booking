package com.example.easemybooking.controller;

import com.example.easemybooking.exception.InvalidTokenException;
import com.example.easemybooking.exception.PaymentFailedException;
import com.example.easemybooking.model.Booking;
import com.example.easemybooking.model.Destination;
import com.example.easemybooking.model.Payment;
import com.example.easemybooking.model.User;
import com.example.easemybooking.service.BookingService;
import com.example.easemybooking.service.DestinationService;
import com.example.easemybooking.service.PaymentService;
import com.example.easemybooking.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookingControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private BookingService bookingService;

    @Mock
    private DestinationService destinationService;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private BookingController bookingController;

    private Booking booking;
    private User user;
    private Destination destination;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("testuser");

        destination = new Destination();
        destination.setDid(1);
        destination.setAdultfee(100);
        destination.setChildfee(50);

        booking = new Booking();
        booking.setUser(user);
        booking.setDestination(destination);
        booking.setAdult_count(2);
        booking.setChildren_count(1);
        booking.setPayment(new Payment());
    }


    @Test
    public void testAddBooking_EmptyUsername() {
        InvalidTokenException exception = assertThrows(InvalidTokenException.class, () -> {
            bookingController.addBooking(booking, "");
        });

        assertEquals("Username cannot be empty", exception.getMessage());

        verify(userService, times(0)).findByUsername(anyString());
        verify(destinationService, times(0)).findByDestinationId(anyInt());
        verify(paymentService, times(0)).addPayment(any(Payment.class));
        verify(bookingService, times(0)).addBooking(any(Booking.class));
    }

    @Test
    public void testAddBooking_NullPayment() {
        booking.setPayment(null);

        PaymentFailedException exception = assertThrows(PaymentFailedException.class, () -> {
            bookingController.addBooking(booking, "anu");
        });

        assertEquals("Payment details are required", exception.getMessage());

        verify(userService, times(1)).findByUsername("anu");
        verify(destinationService, times(1)).findByDestinationId(1);
        verify(paymentService, times(0)).addPayment(any(Payment.class));
        verify(bookingService, times(0)).addBooking(any(Booking.class));
    }

}