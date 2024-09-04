package com.example.easemybooking.controller;

import com.example.easemybooking.ext.LoginRequest;
import com.example.easemybooking.model.Location;
import com.example.easemybooking.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class LocationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    LocationService locationService;

    private String token;

    @BeforeEach
    void setUp() throws Exception {
        // Assuming the login endpoint returns a token in the response body
        String username = "mohan";
        String password = "mohan";
        LoginRequest loginRequest = new LoginRequest(username, password);
        String requestBody = objectMapper.writeValueAsString(loginRequest);

        ResultActions resultActions = mockMvc.perform(post("http://localhost:8080/login")
                .contentType(MediaType.APPLICATION_JSON)
                .param("username", "mohan")
                .param("password", "mohan"));


        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        // Assuming the response is a token directly; adjust if needed
        token = responseContent;
        System.out.println("hello world"+token);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() throws Exception {
        Location location1 = new Location(1, "mangalore");
        Location location2 = new Location(3, "chennai");
        Location location3 = new Location(4, "mysore");
        List<Location> locations = Arrays.asList(location1, location2, location3);

        locationService.add(location1);
        locationService.add(location2);
        locationService.add(location3);

        mockMvc.perform(get("/")
                .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].lid").value(location1.getLocationId()))
                .andExpect(jsonPath("$[0].lname").value(location1.getLocationName()))
                .andExpect(jsonPath("$[1].lid").value(location2.getLocationId()))
                .andExpect(jsonPath("$[1].lname").value(location2.getLocationName()))
                .andExpect(jsonPath("$[2].lid").value(location3.getLocationId()))
                .andExpect(jsonPath("$[2].lname").value(location3.getLocationName()));
    }

    @Test
    void addLocation() throws Exception {
        Location location = new Location(5, "meghalaya");
        String username = "mohan";


        // Act
        ResultActions resultActions = mockMvc.perform(post("/location/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(location))
                .param("username", username)
                .header(HttpHeaders.AUTHORIZATION, token));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.lid").value(location.getLocationId()))
                .andExpect(jsonPath("$.lname").value(location.getLocationName()));
    }

    @Test
    void deleteById() throws Exception {

        Location location = new Location(7, "meghalaya");
        locationService.add(location);
        int lid = 7;
        String username = "mohan";

        ResultActions resultActions = mockMvc.perform(delete("/location/delete/{id}", lid)
                .param("username", username)
                .header(HttpHeaders.AUTHORIZATION, token));


        // Assert
        resultActions.andExpect(status().isOk());


    }

    @Test
    void showLocation() throws Exception {
        int locationId = 1;

        mockMvc.perform(get("/location/{id}", locationId)
                .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.lid").value(locationId))
                .andExpect(jsonPath("$.lname").value("mangalore"));
    }
}
