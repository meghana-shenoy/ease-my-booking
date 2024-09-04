package com.example.easemybooking.service;


import com.example.easemybooking.model.Customer;
import com.example.easemybooking.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    @Override
    public Customer findByCustomerid(int CustomerId) {
        return customerRepo.findById(CustomerId).get();
    }

    @Override
    public Customer findbyEmail(String id) {
        return customerRepo.findByCemail(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
    @Override
    public Customer addCustomer(Customer customer) {
        customerRepo.save(customer);
        System.out.println("customer after saving"  + customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if(customerRepo.existsById(customer.getCustomerId()))
        {
            customerRepo.save(customer);
        }
        else
        {
            throw new RuntimeException("not found");
        }
        return customer;
    }

    @Transactional
    @Override
    public boolean removeById(int CustomerId) {
        Customer customer =customerRepo.findById(CustomerId).get();
        if(customer !=null)
        {
            customerRepo.save(customer);
        }
        else
        {
            throw new RuntimeException("not found");
        }
        return true;
    }



    public boolean checkcustomer(String id) {
        if(customerRepo.existsById(Integer.parseInt(id))) {
            return true;
        }
        else {
            return false;
        }
    }
}
