package com.example.easemybooking.controller;

import com.example.easemybooking.ext.JwtBuilder;
import com.example.easemybooking.model.User;
import com.example.easemybooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    JwtBuilder jwtBuilder;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity checklogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        if(!username.isEmpty() || !password.isEmpty()) {
            logger.info("User with user name " + username + " is trying to login");
            User user = userService.findByUsername(username);
            if(passwordEncoder.matches(password, user.getPassword())) {
                logger.info("Password matches");
                String val  = jwtBuilder.buildJwt(username);
                logger.info("Login Successful");
                return ResponseEntity.ok(val);
            }
            else {
                logger.error("invalid userid or password");
                return ResponseEntity.badRequest().body("invalid userid or password");
            }
        }
        else {
            logger.error("invalid userid or password");
            return ResponseEntity.badRequest().body("invalid userid or password");
        }
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        if (userService.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        logger.info("User registered successfully");
        return user;
    }

}