package com.example.todolist.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 *
 * Created by: Demyd Titenko
 * <p>
 * Date: 22.07.2026
 * <p>
 * Time: 11:37
 * <p>
 * Project name: ToDoList
 */
@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private Key getSingInKey(){
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails){
        Date date = new Date();
        Date expirationDate = new Date(date.getTime() + 1000 * 60 * 60 * 24);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(getSingInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    private Date extractExpirationDate(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){
        return extractExpirationDate(token).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
