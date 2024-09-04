package com.example.easemybooking.repo;

import com.example.easemybooking.model.Booking;
import com.example.easemybooking.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
    List<Booking> findByDestination_DestinationId(int id);
    List<Booking> findByUser_UserId(int id);
}
