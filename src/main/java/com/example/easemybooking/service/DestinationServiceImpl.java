package com.example.easemybooking.service;
import com.example.easemybooking.model.Destination;
import com.example.easemybooking.repo.DestinationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService{
    @Autowired
    DestinationRepo destinationRepo;


    @Override
    public Destination findByDestinationId(int id) {
        System.out.println("hi");
        return destinationRepo.findById(id).orElse(null);
    }

    @Override
    public List<Destination> findDestinationsByLid(int id) {
        return destinationRepo.findByLocation_LocationId(id);
    }

    @Transactional
    @Override
    public Destination addDestination(Destination destination) {
        destinationRepo.save(destination);
        System.out.println("destination after saving"  +destination);
        return destination;
    }

    @Transactional
    @Override
    public Destination updateDestination(Destination destination) {
        if(destinationRepo.existsById(destination.getDid()))
        {
            destinationRepo.save(destination);
        }
        else
        {
            throw new RuntimeException("not found");
        }
        return destination;
    }


    @Override
    public boolean removebyId(int DestinationId) {
        Destination destination=destinationRepo.findById(DestinationId).get();
        if(destination!=null)
        {
            destinationRepo.save(destination);
        }
        else
        {
            throw new RuntimeException("not found");
        }
        return true;
    }
}