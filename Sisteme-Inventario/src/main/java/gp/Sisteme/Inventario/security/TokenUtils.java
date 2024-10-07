package gp.Sisteme.Inventario.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Key;
import java.util.*;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET = "4qhq8LrEBFycaRHxhd9b2URb2nf8e7Ud";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_599_00L;

    public static String crearToken(String username, String password) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("username", username);

        return Jwts.builder()
                .setSubject(password)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAutenticacion(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String password = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(password, null, Collections.emptyList());

        } catch (JwtException e) {
            return null;
        }
    }
}
