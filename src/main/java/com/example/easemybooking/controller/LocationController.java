package com.example.easemybooking.controller;

import com.example.easemybooking.exception.InvalidTokenException;
import com.example.easemybooking.model.Destination;
import com.example.easemybooking.model.Location;
import com.example.easemybooking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class LocationController {
    @Autowired
    LocationService locationService;


    @GetMapping("/")
    @ResponseBody
    public List<Location> findAll() {
            return locationService.findAll();
    }

//    @GetMapping("/")
//    @ResponseBody
//    public List<Location> findAll(@RequestParam String username) {
//        if(username != null) {
//            return locationService.findAll();
//        }
//        else {
//            throw new InvalidTokenException("Invalid Token");
//        }
//    }

    @PostMapping("/location/add")
    public Location addLocation(@RequestBody Location location) {
        System.out.println(location);
        return locationService.add(location);
    }

    @DeleteMapping("/location/delete/{id}")
    public void deleteById(@PathVariable("id") int id) {
        locationService.deleteById(id);
    }

    @GetMapping("/location/{id}")
    public Location showLocation(@PathVariable("id") int id) {
        return locationService.findById(id);
    }
}
