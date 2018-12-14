
package gr.di.uoa.m1542m1552.databasesystems.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import gr.di.uoa.m1542m1552.databasesystems.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static gr.di.uoa.m1542m1552.databasesystems.security.JwtConstants.SIGNING_KEY;
import static gr.di.uoa.m1542m1552.databasesystems.security.JwtConstants.ACCESS_TOKEN_VALIDITY_SECONDS;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        return doGenerateToken(user.getEmail());
    }

    private String doGenerateToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://311-chicago-incidents.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, User user) {
        final String email = getEmailFromToken(token);
        return (
              email.equals(user.getEmail())
                    && !isTokenExpired(token));
    }

}
