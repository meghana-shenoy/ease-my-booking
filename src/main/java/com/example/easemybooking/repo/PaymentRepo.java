package com.example.easemybooking.repo;
import com.example.easemybooking.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
}
