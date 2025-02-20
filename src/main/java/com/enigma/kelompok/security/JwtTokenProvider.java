//package com.enigma.kelompok.security;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtTokenProvider {
//
//  @Value("${app.jwtSecret}")
//  private String jwtSecret;
//
//  @Value("${app.jwtExpirationInMs}")
//  private Long jwtExpirationInMs;
//
//  private final Key key;
//
//  public JwtTokenProvider(String jwtSecret) {
//    this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
//  }
//
//  public String generateToken(Authentication auth) {
//    String username = auth.getName();
//    String roles = auth.getAuthorities().stream()
//        .map(GrantedAuthority::getAuthority)
//        .collect(Collectors.joining(","));
//
//    Date now = new Date();
//    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
//
//    return Jwts.builder()
//        .setSubject(username)
//        .claim("roles", roles)
//        .setIssuedAt(new Date())
//        .setExpiration(expiryDate)
//        .signWith(key)
//        .compact();
//  }
//
//  public String getUsernameFromJwt(String token) {
//    Claims claims = Jwts.parserBuilder()
//        .setSigningKey(key)
//        .build()
//        .parseClaimsJws(token)
//        .getBody();
//
//    return claims.getSubject();
//  }
//
//  public String getRolesFromJwt(String token) {
//    Claims claims = Jwts.parserBuilder()
//        .setSigningKey(key)
//        .build()
//        .parseClaimsJws(token)
//        .getBody();
//
//    return (String) claims.get("roles");
//  }
//
//  public boolean validateToken(String token) {
//    try {
//      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//      return true;
//    } catch (JwtException | IllegalArgumentException e) {
//      return false;
//    }
//  }
//}
