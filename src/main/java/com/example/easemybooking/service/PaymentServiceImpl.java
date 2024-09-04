package com.example.easemybooking.service;

import com.example.easemybooking.model.Location;
import com.example.easemybooking.model.Payment;
import com.example.easemybooking.repo.OwnersRepo;
import com.example.easemybooking.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepo paymentRepo;

    @Override
    @Transactional
    public Payment addPayment(Payment payment) {
        paymentRepo.save(payment);
        System.out.println("location after saving:"+payment);
        return payment;
    }

    @Override
    public Payment findById(int id) {
        return paymentRepo.findById(id).orElse(null);
    }

}
