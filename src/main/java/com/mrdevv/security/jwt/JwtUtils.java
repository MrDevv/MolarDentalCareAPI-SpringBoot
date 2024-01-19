package com.mrdevv.security.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    //    Genera el token de acceso
    public String generateAccessToken(String username, String role) {
        return Jwts.builder()
                .claim("authorities", role)
                .subject(username)
                .signWith(SECRET_KEY)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .compact();
    }

    //    Valida el token
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    //    Obtiene el usuario del token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
