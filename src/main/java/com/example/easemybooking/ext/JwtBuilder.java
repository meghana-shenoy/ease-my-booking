package com.example.easemybooking.ext;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtBuilder {

    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String buildJwt(String name)
    {
        String jwtToken = Jwts.builder()
                .claim("name", name)
                .signWith(secretKey)
                .compact();
        System.out.println(jwtToken);
        return jwtToken;

    }
    public Jwt<?,?> parseJwt(String jwtString) {

        Jwt<?, ?> jwt = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parse(jwtString);
        Header header = jwt.getHeader();
        Object payload = jwt.getPayload();
        System.out.println(header);
        System.out.println(payload);
        return jwt;
    }



//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    public static void main(String[] args) {
        //JwtBuilder obj = new JwtBuilder();
      //  String jwtString = obj.buildJwt("Meghana", "meghana@gmail.com");
       // obj.parseJwt(jwtString);

    }
}
