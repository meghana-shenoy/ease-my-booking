package com.example.easemybooking.controller;
import com.example.easemybooking.ext.LoginRequest;
import com.example.easemybooking.model.Location;
import com.example.easemybooking.service.CustomerService;
import com.example.easemybooking.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        int id = 1;
        String password = "test";
        LoginRequest loginRequest = new LoginRequest(id, password);
        String requestBody = objectMapper.writeValueAsString(loginRequest);

        ResultActions resultActions = mockMvc.perform(post("http://localhost:8080/login?username=2&password=mohan"));
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody));

        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        // Assuming the response is a token directly; adjust if needed
        token = responseContent;
        System.out.println("hello world"+token);
    }

    @AfterEach
    void tearDown() {
        // Clean up resources if needed
    }

    @Test
    void findAll() throws Exception {
        Location location1 = new Location(1, "mangalore");
        Location location2 = new Location(2, "bangalore");
        Location location3 = new Location(3, "chennai");
        Location location4 = new Location(4, "mysore");
        List<Location> locations = Arrays.asList(location1, location2, location3, location4);

        locationService.add(location1);
        locationService.add(location2);
        locationService.add(location3);
        locationService.add(location4);

        mockMvc.perform(get("/")
                .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].lid").value(location1.getLid()))
                .andExpect(jsonPath("$[0].lname").value(location1.getLname()))
                .andExpect(jsonPath("$[1].lid").value(location2.getLid()))
                .andExpect(jsonPath("$[1].lname").value(location2.getLname()))
                .andExpect(jsonPath("$[2].lid").value(location3.getLid()))
                .andExpect(jsonPath("$[2].lname").value(location3.getLname()))
                .andExpect(jsonPath("$[3].lid").value(location4.getLid()))
                .andExpect(jsonPath("$[3].lname").value(location4.getLname()));
    }
//
//    @Test
//    void addLocation() throws Exception {
//        // Implement your test for adding a location
//    }
//
//    @Test
//    void deleteById() throws Exception {
//        // Implement your test for deleting a location by id
//    }

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

//    @Test
//    public void testReturnTextWithValidUsername() throws Exception {
//        when(locationService.findAll()).thenReturn(locations);
//        MvcResult result = mockMvc.perform(get("/location")
//                .param("username", "validUser"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1L))
//                .andExpect(jsonPath("$[0].name").value("Location 1"))
//                .andExpect(jsonPath("$[1].id").value(2L))
//                .andExpect(jsonPath("$[1].name").value("Location 2"))
//                .andReturn();
//        // Print the response for debugging
//        System.out.println(result.getResponse().getContentAsString());
//    }
//    @Test
//    public void testReturnTextWithNullUsername() throws Exception {
//        mockMvc.perform(get("/location"))
//                .andExpect(status().isBadRequest());
//    }











