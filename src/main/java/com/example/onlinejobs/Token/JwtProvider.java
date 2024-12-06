package com.example.onlinejobs.Token;

import com.example.onlinejobs.Entity.RoleTable.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class JwtProvider {

    @Value("${spring.security.jwt.token.Secret}")
    private String secretKey;
    @Value("${spring.security.jwt.token.Validity}")
    private long Validity;

    public String generateToken(String username, Set<Role> roles, String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("email", email);
        return createToken(username, claims);
    }

    private String createToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSecretKey())
                .expiration(new Date(System.currentTimeMillis() + Validity))
                .compact();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims AllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new SecurityException("jwt token mosligida hatolik");
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !IsExpired(token));
    }

    private boolean IsExpired(String token) {
        return getExpiredDate(token).before(new Date(System.currentTimeMillis()));
    }

    private Date getExpiredDate(String token) {
        Claims claims = AllClaims(token);
        return claims.getExpiration();
    }

    public String getUsername(String token) {
        Claims claims = AllClaims(token);
        return claims.getSubject();
    }
}
