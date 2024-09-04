package com.example.easemybooking.service;

import com.example.easemybooking.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer findByCustomerid(int CustomerId);
    List<Customer> findAll();
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    boolean removeById(int CustomerId);
    Customer findbyEmail(String id);
}