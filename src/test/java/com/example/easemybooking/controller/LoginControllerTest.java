package com.example.easemybooking.controller;

import com.example.easemybooking.ext.JwtBuilder;
import com.example.easemybooking.model.User;
import com.example.easemybooking.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtBuilder jwtUtil;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private LoginController loginController;


    @Test
    void loginSuccess() throws Exception {
        String jwtPattern = "^([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_\\-\\+\\/=]*)$";
        ResultActions result = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .param("username", "meghana")
                .param("password", "meghana"));
        String responseContent = result.andReturn().getResponse().getContentAsString();
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(content().string(Matchers.matchesPattern(responseContent)));
    }



    @Test
    public void loginFailure() throws Exception {

        String jwtPattern = "^([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_\\-\\+\\/=]*)$";
        ResultActions result = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .param("username", "manya")
                .param("password", "aaa"));

        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testRegister_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        Mockito.when(userService.findByUsername("testuser")).thenReturn(null);
        Mockito.when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        User registeredUser = loginController.register(user);

        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
        assertEquals("encodedPassword", registeredUser.getPassword());

        verify(userService, times(1)).findByUsername("testuser");
        verify(passwordEncoder, times(1)).encode("password");
        verify(userService, times(1)).addUser(user);
    }

    @Test
    public void testRegister_UserAlreadyExists() {
        User user = new User();
        user.setUsername("existinguser");
        user.setPassword("password");

        when(userService.findByUsername("existinguser")).thenReturn(new User());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            loginController.register(user);
        });

        assertEquals("User already exists!", exception.getMessage());

        verify(userService, times(1)).findByUsername("existinguser");
        verify(passwordEncoder, times(0)).encode(anyString());
        verify(userService, times(0)).addUser(any(User.class));
    }

}
