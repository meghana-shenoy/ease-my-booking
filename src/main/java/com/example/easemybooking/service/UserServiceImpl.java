package com.example.easemybooking.service;


import com.example.easemybooking.model.User;
import com.example.easemybooking.model.UserType;
import com.example.easemybooking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findByUserId(int userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public User addUser(User user) {
        userRepo.save(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if(userRepo.existsById(user.getUserId()))
            userRepo.save(user);
        else
            throw new RuntimeException("not found");
        return user;
    }

    @Override
    public boolean removeByUserId(int userId) {
        userRepo.deleteById(userId);
            return true;
    }

    @Override
    public User findByUsername(String userName) {
        return userRepo.findByUsername(userName);
    }

    @Override
    public List<User> findAllCustomers() {
        return userRepo.findByUserType(UserType.CUSTOMER);
    }

    @Override
    public List<User> findAllOwners() {
        return userRepo.findByUserType(UserType.OWNER);
    }

    @Override
    public User findByCustomerId(int userId) {
        return userRepo.findByUserId(userId);
    }

    @Override
    public User findByOwnerId(int userId) {
        return userRepo.findByUserId(userId);
    }
}


