package com.depgirpro.securitytokenlogin.service;

import com.depgirpro.securitytokenlogin.model.Persona;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {

    @Value("${security.jwt.expiration-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generarToken(Persona persona, Map<String, Object> stringObjectMap){

        Date issuedAt=new Date(System.currentTimeMillis());
        Date expiration=new Date(issuedAt.getTime() + (EXPIRATION_IN_MINUTES*60*1000));

        return Jwts.builder()
                .setClaims(stringObjectMap)
                .setSubject(persona.getCorreo())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key generateKey(){
        byte[] secretAsBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretAsBytes);
    }


    public String extractCorreo(String jwt){
        return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody().getSubject();
    }
}
