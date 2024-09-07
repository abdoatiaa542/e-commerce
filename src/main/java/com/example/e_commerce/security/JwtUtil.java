package com.example.e_commerce.security;

import com.example.e_commerce.models.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshTokenExpiration;

    // استخراج البريد الإلكتروني من التوكن
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // استخراج تاريخ انتهاء صلاحية التوكن
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // التأكد من انتهاء صلاحية التوكن
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // توليد Access Token
    public String generateAccessToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getEmail(), jwtExpiration);
    }

    // توليد Refresh Token
    public String generateRefreshToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getEmail(), refreshTokenExpiration);
    }

    // إنشاء توكن جديد
    private String createToken(Map<String, Object> claims, String subject, long expiration) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // التحقق من صحة التوكن
    public Boolean validateToken(String token, User userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }

    // استخراج مطالبة من التوكن
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // استخراج جميع المطالبات من التوكن
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
