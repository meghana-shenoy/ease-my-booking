package com.example.easemybooking.repo;

import com.example.easemybooking.model.Destination;
import com.example.easemybooking.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepo extends JpaRepository<Destination, Integer> {
       List<Destination> findByLocation_Lid(int lid);
//    Destination deleteByDname(String dname);
}