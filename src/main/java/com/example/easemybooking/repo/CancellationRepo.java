package com.example.easemybooking.repo;

import com.example.easemybooking.model.Booking;
import com.example.easemybooking.model.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancellationRepo extends JpaRepository<Cancellation, Integer>{
}


