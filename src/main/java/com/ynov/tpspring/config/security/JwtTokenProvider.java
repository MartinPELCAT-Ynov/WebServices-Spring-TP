package com.ynov.tpspring.config.security;

import com.ynov.tpspring.entities.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;


@Component
public class JwtTokenProvider {

    @Value("${JWT_SECRET}")
    private String secretKey;


    private final long validityInMilliseconds = 43200000;  // 12h

    @Autowired
    private CustomUserDetails myUserDetails;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    private Claims createTokenSubject(User user) {
        String username = user.getUsername();
        String centerUuid = user.getCenter().getUuid();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("username", username);
        claims.put("center", centerUuid);
        return claims;
    }


    public String createToken(User user) {

        Claims claims = createTokenSubject(user);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        String centerUuid = claims.getBody().get("center", String.class);
        String username = claims.getBody().get("username", String.class);
        UserDetails userDetails = myUserDetails.loadUserByUserNameAndCenter(username, centerUuid);
        System.out.println("UserDetails ".concat(userDetails.getUsername()));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new Exception("Expired or invalid JWT token");
        }
    }

}
