package com.codeplatform.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET = "change-this-secret-to-env-var-32-bytes-minimum-key!";
    private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000L;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generate(String subject, Map<String, Object> claims) {
        return Jwts.builder()
            .setSubject(subject)
            .addClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
            .signWith(key(), SignatureAlgorithm.HS256)
            .compact();
    }

    public Claims parse(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}


