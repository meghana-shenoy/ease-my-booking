package com.example.easemybooking.controller;
import com.example.easemybooking.model.Customer;
import com.example.easemybooking.service.CustomerService;
import com.example.easemybooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @GetMapping("/customers")
    public List<Customer> getAllCustomer()
    {
        return customerService.findAll();
    }


    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id")int customerId){
        Customer customer =customerService.findByCustomerid(customerId);
        return customer;
    }

//    @PostMapping("/addcustomer")
//    public Customer addCustomer(@RequestBody Customer customer)
//    {
//        customer = customerService.addCustomer(customer);
//        return customer;
//    }

    @PutMapping("/customer")
    public Customer updateCustomer(@RequestBody Customer customer)
    {
        return customerService.updateCustomer(customer);

    }

    @DeleteMapping("/customer/{id}")
    public boolean deleteCustomer(@PathVariable("id")int customerId){
        return customerService.removeById(customerId);
    }

}
