package com.example.easemybooking.controller;

import com.example.easemybooking.exception.InvalidTokenException;
import com.example.easemybooking.model.Location;
import com.example.easemybooking.model.User;
import com.example.easemybooking.model.UserType;
import com.example.easemybooking.service.LocationService;
import com.example.easemybooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PutMapping("/updateCustomer")
    public User updateCustomer(@RequestBody User user, @RequestParam String username) {
        logger.info("updateCustomer method called");
        if (username != null) {
            System.out.println(username);
            User currentUser = userService.findByUsername(username);
            if (currentUser.getUserType().name().equals(UserType.CUSTOMER.name())) {
                System.out.println(user);
                logger.info("Updated customer");
                return userService.updateUser(user);
            } else {
                logger.error("Update Customer failed!!!");
                throw new InvalidTokenException("Invalid Token");
            }
        } else {
            logger.error("Update Customer failed!!!");
            throw new InvalidTokenException("Invalid Token");
        }
    }

    @PutMapping("/updateOwner")
    public User updateOwner(@RequestBody User user, @RequestParam String username) {
        logger.info("updateOwner method called");
        if (username != null) {
            System.out.println(username);
            User currentUser = userService.findByUsername(username);
            if (currentUser.getUserType().name().equals(UserType.OWNER.name())) {
                System.out.println(user);
                logger.info("Updated owner");
                return userService.updateUser(user);
            } else {
                logger.error("Update Owner failed!!!");
                throw new InvalidTokenException("Invalid Token");
            }
        } else {
            logger.error("Update Owner failed!!!");
            throw new InvalidTokenException("Invalid Token");
        }
    }

    @GetMapping("/findAllCustomers")
    public List<User> findAllCustomers() {
        logger.info("Finding All Customers");
        return userService.findAllCustomers();
    }

    @GetMapping("/findAllOwners")
    public List<User> findAllOwners() {
        return userService.findAllOwners();
    }

    @GetMapping("/findCustomerById/{id}")
    public User findByCustomerId(@PathVariable int userId) {
        return userService.findByCustomerId(userId);
    }

    @GetMapping("/findOwnerByID/{id}")
    public User findByOwnerId(@PathVariable int userId) {
        return userService.findByOwnerId(userId);
    }
}