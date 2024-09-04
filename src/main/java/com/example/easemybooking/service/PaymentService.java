package com.example.easemybooking.service;

import com.example.easemybooking.model.Payment;

public interface PaymentService {
    Payment addPayment(Payment payment);
    Payment findById(int id);
    Float calculateTotalTicketCost(Payment payment);
    Payment updatePayment(Payment payment);
}
