package com.example.easemybooking.service;

import com.example.easemybooking.model.Cancellation;
import com.example.easemybooking.model.Destination;
import com.example.easemybooking.repo.CancellationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancellationServiceImpl implements CancellationService{

    @Autowired
    CancellationRepo cancellationRepo;

    @Transactional
    @Override
    public Cancellation addCancellation(Cancellation cancellation) {
        cancellationRepo.save(cancellation);
        System.out.println("destination after saving"  +cancellation);
        return cancellation;
    }

    @Override
    public Cancellation findCancellation(int id) {
        System.out.println("hi");
        return cancellationRepo.findById(id).orElse(null);
    }
}
