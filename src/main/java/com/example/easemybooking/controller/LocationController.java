package com.example.easemybooking.controller;

import com.example.easemybooking.exception.InvalidTokenException;
import com.example.easemybooking.model.Destination;
import com.example.easemybooking.model.Location;
import com.example.easemybooking.model.User;
import com.example.easemybooking.model.UserType;
import com.example.easemybooking.service.LocationService;
import com.example.easemybooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Component
@RestController
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    LocationService locationService;

    @Autowired
    UserService userService;

    @GetMapping("/location")
    @ResponseBody
    public List<Location> returnText(@RequestParam (required = false) String username) {
        System.out.println(username);
        return locationService.findAll();
    }

    @PostMapping("/addLocation")
    public Location addLocation(@RequestBody Location location, @RequestParam String username) {
        logger.info("adding Location");
        if (username != null) {
            System.out.println(username);
            User user = userService.findByUsername(username);
            if (user.getUserType().name().equals(UserType.OWNER.name())) {
                System.out.println(location);
                logger.info("adding Location successful");
                return locationService.add(location);
            } else {
                logger.error("Failed to add Location");
                throw new InvalidTokenException("The user is not authorized to perform this operation");
            }
        } else {
            throw new InvalidTokenException("Invalid Token");
        }

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