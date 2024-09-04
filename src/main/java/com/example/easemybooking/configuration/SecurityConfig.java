package com.example.easemybooking.configuration;

import com.example.easemybooking.ext.LoginFilter;
import com.example.easemybooking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   // @Autowired
    // private UserDetailsService userDetailsService;

    @Autowired
    private CustomerService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder().encode(rawPassword);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                User user = userService.findByUsername(username);
//                if (user == null) {
//                    throw new UsernameNotFoundException("User not found");
//                }
//                return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
//            }
//        };
//    }
}
