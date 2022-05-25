package com.mns.locmns.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtUtils {

    @Value("${secret}")
    private String secret;

    // fonction qui permet de récupérer le corp du token
    public Claims getTokenBody (String token) {
        return Jwts.parser()
                .setSigningKey("azerty123")
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetail userDetails) {
        Map<String,Object> donnees=new HashMap<>();

        donnees.put("login",userDetails.getUtilisateur().getLogin());
        donnees.put("id",userDetails.getUtilisateur().getId());

        String listeDroits = userDetails.
                getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.joining(","));

        donnees.put("droits",listeDroits);

        return Jwts.builder()
                .setClaims(donnees)
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean tokenValid(String token, UserDetails userDetails) {
        Claims claims = getTokenBody(token);

        return (claims.getSubject().equals(userDetails.getUsername()));
    }
}