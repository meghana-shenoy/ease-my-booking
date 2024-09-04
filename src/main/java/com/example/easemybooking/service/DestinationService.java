package com.example.easemybooking.service;

import com.example.easemybooking.model.Destination;

import java.util.List;

public interface DestinationService {
    Destination addDestination(Destination destination);
    Destination updateDestination(Destination destination);
    boolean removebyId(int DestinationId);
    List<Destination> findDestinationsByLid(int id);
    Destination findDestinationByDid(int id);

}
