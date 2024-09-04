package com.example.easemybooking.ext;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;



@Component
public class
JwtBuilder {
    String secret = "My Long Secret Key as Short Key is not allowed";

    public String buildJwt(String name)
    {
        SecretKey key =  Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        String jwtToken = Jwts.builder()
                .claim("name", name)
                .signWith(key)
                .compact();
        System.out.println(jwtToken);
        return jwtToken;

    }
    public String parseJwt(String jwtString) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Jwt<?, ?> jwt = Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(jwtString);
        Header header = jwt.getHeader();
        Object payload = jwt.getPayload();
        System.out.println(header);
        System.out.println(payload);
        Claims claims = (Claims) payload;
        Object nameField = claims.get("name");
        System.out.println(nameField.toString());

//        JsonNode json = (JsonNode) payload;
//        JsonNode nameNode = json.get("name");
        return nameField.toString();
    }

    public static void main(String[] args) {
//        JwtBuilder obj = new JwtBuilder();
//        String jwtString = obj.buildJwt("Meghana", "meghana@gmail.com");
//        obj.parseJwt(jwtString);

    }
}