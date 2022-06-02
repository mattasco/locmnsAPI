package com.mns.locmns.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtUtils {

    @Value("${secret}")
    private String secret;

    // fonction qui permet de récupérer le corps du token
    public Claims getTokenBody (String token) {
        return Jwts.parser()
                .setSigningKey("azerty123")
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetail userDetails) {

        Map<String,Object> donnees=new HashMap<>();

        donnees.put("id",userDetails.getUtilisateur().getId());

        String listeDroits = userDetails.
                getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.joining(","));

        donnees.put("droits",listeDroits);

        Calendar dateAujd=Calendar.getInstance();
        long dateAujdEnSecondes=dateAujd.getTimeInMillis();
        /*Date dateExpiration=new Date(dateAujdEnSecondes+(10*60*1000));*/
        donnees.put("numToken",userDetails.getUtilisateur().getNumToken());

        return Jwts.builder()
                .setClaims(donnees)
                /*.setExpiration(dateExpiration) activer expiration*/
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean tokenValid(String token, UserDetail userDetails) {
        Claims claims = getTokenBody(token);
        boolean utilisateurValid = claims.getSubject().equals((userDetails.getUsername()));
        boolean numTokenValid = claims.get("numToken").equals(userDetails.getUtilisateur().getNumToken());
        return utilisateurValid && numTokenValid;
    }
}