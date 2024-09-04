package com.example.easemybooking.service;
import com.example.easemybooking.model.Location;
import com.example.easemybooking.repo.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationRepo locationRepo;

    @Override
    public Location findById(int id) {
        return locationRepo.findById(id).orElse(null);
    }

    @Override
    public List<Location> findAll() {
        return locationRepo.findAll();
    }

    @Override
    @Transactional
    public Location add(Location l) {
        locationRepo.save(l);
        System.out.println("location after saving:"+l);
        return l;
    }

    @Override
    @Transactional
    public boolean deleteById(int id) {
        locationRepo.deleteById(id);
        System.out.println("hi");
        return true;
    }

//    public boolean deleteByName(String name) {
//        locationRepo.deleteByLname(name);
//        return true;
//    }

//    public Location findByName(String lname) {
//        return locationRepo.findByLname(lname);
//    }
}