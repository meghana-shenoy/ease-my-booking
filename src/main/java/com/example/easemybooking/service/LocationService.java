package com.example.easemybooking.service;

import com.example.easemybooking.model.Destination;
import com.example.easemybooking.model.Location;

import java.util.List;

public interface LocationService {
    Location findById(int id);
    List<Location> findAll();
    Location add(Location l);
    boolean deleteById(int bid);
}



