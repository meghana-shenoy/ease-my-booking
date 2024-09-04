package com.example.easemybooking.repo;

import com.example.easemybooking.model.User;
import com.example.easemybooking.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByUserType(UserType userType);
    User findByUserId(int userId);
    User findByUsername(String userName);
}
