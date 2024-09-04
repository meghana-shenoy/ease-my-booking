package com.example.easemybooking.ext;


import com.fasterxml.jackson.databind.JsonNode;
import io.jsonwebtoken.Jwt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {

            HttpServletRequest req = (HttpServletRequest) servletRequest;
            System.out.println("url = " + req.getRequestURL());

            if(req.getRequestURL().toString().endsWith("/") ||
                    req.getRequestURL().toString().endsWith("/login") ||
                    req.getRequestURL().toString().endsWith("/register") ||
                req.getRequestURL().toString().endsWith("/logout"))
            {
                System.out.println("exempted Urls , go ahead");
                filterChain.doFilter(servletRequest,servletResponse);
                return ;
            }
            else
            {
                String auth  = req.getHeader("Authorization");
                System.out.println("token:" + auth );
                if(auth !=null) {
                    String username = verified(auth);
                    if(username != null ) {
                        servletRequest.setAttribute("username", username);
                        filterChain.doFilter(servletRequest,servletResponse);
                        return ;
                    }
                }


                servletResponse.getWriter().println("unauthorized access");
                // no filter chain
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

    private String verified(String auth) {
        auth = auth.substring(7);
        // jwtBuilder match the token
        Jwt<?, ?> jwt = jwtBuilder.parseJwt(auth);
        Object payload = jwt.getPayload();
        JsonNode json = (JsonNode) payload;
        return json.get("username").toString();

    }
}
