package com.example.easemybooking.controller;

import com.example.easemybooking.model.Destination;
import com.example.easemybooking.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class DestinationController {
    @Autowired
    DestinationService destinationService;


    @GetMapping("/location/{id}/destinations")
    public List<Destination> findbylid(@PathVariable("id") int id) {
        return destinationService.findDestinationsByLid(id);
    }

    @GetMapping("/location/destination/{did}")
    public Destination findbyid(@PathVariable("did") int id) {
        return destinationService.findDestinationByDid(id);
    }


    @PostMapping("/location/destination/add")
    public Destination addLocation(@RequestBody Destination destination) {
        System.out.println(destination);
        return destinationService.addDestination(destination);
    }

    @DeleteMapping("/location/destination/delete/{id}")
    public void removeById(@PathVariable("id") int id) {
        destinationService.removebyId(id);
    }

    @PutMapping("/location/destination/update")
    public Destination updateTheDestination(@RequestBody Destination destination) {
        return destinationService.updateDestination(destination);
    }

}
