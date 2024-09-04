package com.example.easemybooking.service;

import com.example.easemybooking.model.User;

import java.math.BigInteger;
import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findByUserId(int userId);
    User addUser(User user);
    User updateUser(User user);
    boolean removeByUserId(int userId);
    User findByUsername(String userName);
    List<User> findAllCustomers();
    List<User> findAllOwners();
    User findByCustomerId(int userId);
    User findByOwnerId(int userId);
}
