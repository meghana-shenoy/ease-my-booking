package com.example.easemybooking.controller;

import com.example.easemybooking.model.Customer;
import com.example.easemybooking.model.Location;
import com.example.easemybooking.model.Payment;
import com.example.easemybooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/booking/addpayment")
    public Payment addPayment(@RequestBody Payment payment)
    {
        return paymentService.addPayment(payment);
    }

    @GetMapping("/booking/payment/{id}")
    public Payment showPayment(@PathVariable("id") int id) {
        return paymentService.findById(id);
    }

}
