package com.example.easemybooking.repo;

import com.example.easemybooking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Customer findByCemail(String id);
}
