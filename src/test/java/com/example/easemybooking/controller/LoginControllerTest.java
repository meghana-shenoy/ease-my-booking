package com.example.easemybooking.controller;


import com.example.easemybooking.ext.LoginRequest;
import com.example.easemybooking.model.Customer;
import com.example.easemybooking.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private LoginController loginController;

    @Test
    void login() throws Exception {
        LoginRequest loginRequest = new LoginRequest(3, "password");
        // Convert LoginRequest to JSON
        String requestBody = objectMapper.writeValueAsString(loginRequest);
        // JWT pattern matcher
        String jwtPattern = "^([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_\\-\\+\\/=]*)$";
        // Perform the request
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // Assert status and response
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("token").value(Matchers.matchesPattern(jwtPattern)));
    }




//    @Test
//    void InvalidPassword() throws Exception {
//        LoginRequest loginRequest = new LoginRequest(4, "wrongpassword");
//        ResultActions result = mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(String.valueOf(loginRequest)));
//        result.andExpect(status().isBadRequest());
//    }
//    @Test
//    void UnknownUser() throws Exception{
//        LoginRequest loginRequest = new LoginRequest("xyz@gmail.com", "password");
//        ResultActions result = mockMvc.perform(post("/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(String.valueOf(loginRequest)));
//        result.andExpect(status().isBadRequest());
//    }

    @Test
    void createCustomer() throws Exception {
        String email = "test2@example.com";
        String password = "password";
        String fname = "test";
        String lname = "case";
        String contactNo = "123456789";
        String mname = ".";

//        String customerType = "USER";
        Customer newCustomer = new Customer();
        newCustomer.setCemail(email);
        newCustomer.setPassword(password);
        newCustomer.setCfname(fname);
        newCustomer.setClname(lname);
        newCustomer.setCcphoneno(contactNo);
        newCustomer.setCmidname(mname);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCustomer)));

        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("Response Content: " + responseContent);
        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("cfname").value(fname));
    }




//
//    @Test
//    void customerExists() throws Exception{
//        String name = "test";
//        String contactNo = "123456789";
//        int locationId = 10;
//        String email = "test@example.com";
//        String password = "password";
//        String customerType = "USER";
//        Customer existingCustomer = new Customer();
//        existingCustomer.setCustomerEmail(email);
//        existingCustomer.setCustomerPassword(password);
//        existingCustomer.setCustomerName(name);
//        existingCustomer.setCustomerType(customerType);
//        existingCustomer.setCustomerPhone(contactNo);
//        existingCustomer.setLocationId(locationId);
//        // When
//        ResultActions resultActions = mockMvc.perform(post("/sign-up")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(existingCustomer)));
//        // Then
//        resultActions.andExpect(status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
//                .andExpect(MockMvcResultMatchers.content().string("Customer already exists"));
//    }
}






