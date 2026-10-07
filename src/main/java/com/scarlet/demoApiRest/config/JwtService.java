package com.scarlet.demoApiRest.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;
    private final long expirationMs;

    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("${app.jwt.expiration-ms}") long expirationMs) {

        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.expirationMs = expirationMs;
    }

    public String genererToken(UserDetails user) {

        Date maintenant = new Date();

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(maintenant)
                .expiration(new Date(maintenant.getTime() + expirationMs))
                .signWith(key)
                .compact();
    }

    public String extraireEmail(String token) {
        return lireClaims(token).getSubject();
    }

    public boolean estValide(String token, UserDetails user) {

        try {
            return lireClaims(token).getSubject().equals(user.getUsername());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }

    private Claims lireClaims(String token) {

        // Vérifie la signature ET l'expiration ; lève une JwtException si l'une est mauvaise
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload();
    }

}
