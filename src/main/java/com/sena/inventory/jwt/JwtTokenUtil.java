package com.sena.inventory.jwt;

import com.sena.inventory.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    public static final long JWT_TOKEN_VALIDITY = 3600; // 1 hora

    @Value("${jwt.secret}")
    private String secret;
    //Devuelve el nombre de usuario a partir del token
    public String getUsernameToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }
    //Devuelve la fecha de expiracion del Token
    public Date getExpirationDateToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }
    //Devuelve lo que se le pida, username o la fecha de expiracion
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //Trae cualquier informacion y para ello es necesario el SECRET
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    //Validamos que el token no supere la fecha actual, es decir si expiró o no
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateToken(token);
        return expiration.before(new Date());
    }

    //Generar token
    public String generateToken(UserDetails user){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails user){
        final String username = getUsernameToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }
}
