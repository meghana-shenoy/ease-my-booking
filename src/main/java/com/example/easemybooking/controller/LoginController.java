package com.example.easemybooking.controller;

import com.example.easemybooking.ext.JwtBuilder;
import com.example.easemybooking.model.Customer;
import com.example.easemybooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    @Autowired
    JwtBuilder jwtBuilder;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomerService CustomerService;

    public static String checkusername="hello";

    @PostMapping("/login")
    public ResponseEntity checklogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (!username.isEmpty() || !password.isEmpty()) {
            checkusername = username;
            int usernameINT = Integer.parseInt(username);
            Customer a = CustomerService.findByCustomerid(usernameINT);
            System.out.println(a.getCustomerId());
            System.out.println(a.getPassword());
            System.out.println(username+password);
            System.out.println(passwordEncoder);
            if (passwordEncoder.matches(password, a.getPassword())) {
                System.out.println("Password matches");
                String val = jwtBuilder.buildJwt(username);
                return ResponseEntity.ok(val);
            }
            else {
                System.out.println("hi");
                return ResponseEntity.badRequest().body("invalid userid or password");
            }
        }
        else return ResponseEntity.badRequest().body("invalid userid or password");
    }

    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer) {

        System.out.println("customer details is:"+customer);
        if (CustomerService.findByCustomerid(customer.getCustomerId()) != null) {
            throw new RuntimeException("User already exists!");
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        CustomerService.addCustomer(customer);
        return customer;
    }

}

