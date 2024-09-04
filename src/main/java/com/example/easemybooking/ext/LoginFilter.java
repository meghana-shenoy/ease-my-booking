package com.example.easemybooking.ext;


import com.example.easemybooking.controller.LoginController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter
@Component
public class LoginFilter implements Filter {

    private final JwtBuilder jwtBuilder;
    private final PasswordEncoder passwordEncoder;

    public LoginFilter(JwtBuilder jwtBuilder, PasswordEncoder passwordEncoder) {
        this.jwtBuilder = jwtBuilder;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            System.out.println("url = " + req.getRequestURL());

            if(req.getRequestURL().toString().endsWith("/") ||
                    req.getRequestURL().toString().endsWith("/login") || req.getRequestURL().toString().endsWith("/register"))
            {
                System.out.println("exempted Urls , go ahead");
                filterChain.doFilter(servletRequest,servletResponse);
                return ;
            }
            else
            {
                String auth  = req.getHeader("Authorization");
                System.out.println("token:" + auth );
                System.out.println(LoginController.checkusername);
                String jwt = "";
                try {
                    jwt = jwtBuilder.parseJwt(auth);
                    if(jwt.equals(LoginController.checkusername)) {
                        System.out.println("successful!");
                        filterChain.doFilter(servletRequest,servletResponse);
                        return ;
                    }
                }
                catch (Exception e) {
                    servletResponse.getWriter().println("unauthorized access");
                }
                System.out.println("hi");
//                if(customerService.checkcustomer(jwt)) {
//                    System.out.println("successful!");
//                    filterChain.doFilter(servletRequest,servletResponse);
//                    return ;
//                }
//                else {
//                    servletResponse.getWriter().println("unauthorized access");
//                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


//    private String verified(String auth) {
//        auth = auth.substring(7);
//        Jwt<?, ?> jwt = jwtBuilder.parseJwt(auth);
//        Object payload = jwt.getPayload();
//        JsonNode json = (JsonNode) payload;
//        return json.get("username").toString();
//    }
}