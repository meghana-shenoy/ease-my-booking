package com.example.easemybooking.controller;

import com.example.easemybooking.exception.InvalidTokenException;
import com.example.easemybooking.model.Destination;
import com.example.easemybooking.model.Location;
import com.example.easemybooking.model.User;
import com.example.easemybooking.model.UserType;
import com.example.easemybooking.service.DestinationService;
import com.example.easemybooking.service.LocationService;
import com.example.easemybooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class DestinationController {

    @Autowired
    DestinationService destinationService;

    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    @GetMapping("/location/{id}/destinations")
    public List<Destination> findbylid(@PathVariable("id") int id) {
        return destinationService.findDestinationsByLid(id);
    }

    @GetMapping("/location/destination/{did}")
    public Destination findbyid(@PathVariable("did") int id) {
        return destinationService.findByDestinationId(id);
    }


    @PostMapping("/addDestination")
    public Destination addDestination(@RequestBody Destination destination, @RequestParam String username) {
        if (username != null && !username.isEmpty()) {
            System.out.println(username);
            User user = userService.findByUsername(username);
            if (user.getUserType().name().equals(UserType.OWNER.name())) {
                System.out.println(destination);
                Location location = locationService.findById(destination.getLocation().getLocationId());
                if (location != null) {
                    destination.setLocation(location);
                }
                destination.setOwner(user);
                return destinationService.addDestination(destination);
            } else {
                throw new InvalidTokenException("Invalid Token");
            }
        } else {
            throw new InvalidTokenException("Invalid Token");
        }
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