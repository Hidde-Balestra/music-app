package teamx.musiccollabbackend.domain.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import teamx.musiccollabbackend.domain.User.UserModel;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class JWTUtil {
    public final static String JWT_AUTHORIZATION_TOKEN_PREFACE = "Bearer ";
    public final static long JWT_DURATION_IN_MILLISECONDS = 24 * 60 * 60 * 1000;

    private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);

    @Autowired
    private JWTConfig config;

    public String generateTokenFor(UserModel user) {
        return Jwts.builder()
                .setSubject(String.format("%s", user.getEmail()))
                .claim("roles", Objects.requireNonNullElse(user.getAccountType(), List.of()))
                .setIssuer(config.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_DURATION_IN_MILLISECONDS))
                .signWith(JWTUtil.getKey(config.getPassPhrase()), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validate(String token) {
        try {
            parseToken(token, config.getPassPhrase());

            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature - {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token - {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token - {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token - {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty - {}", e.getMessage());
        }

        return false;
    }

    public String getEmailOf(String token) {
        return parseToken(token, config.getPassPhrase()).getBody().getSubject();
    }

    public Date getExpirationOf(String token) {
        return parseToken(token, config.getPassPhrase()).getBody().getExpiration();
    }

    /**
     * Generates a key based on secret and unique string.
     *
     * @param passPhrase A string that is secret and unique
     * @return A key that can be used to en-/decrypt a JWT
     */
    private static Key getKey(String passPhrase) {
        byte[] hmacKey = passPhrase.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(hmacKey, SignatureAlgorithm.HS512.getJcaName());
    }

    /**
     * Parses the body of a JWT in string format, with the provided passphrase, to a JWS object.
     *
     * @param token      A JWT in string format.
     * @param passPhrase A string that is secret and unique
     * @return The body of the JWT in a JWS object.
     */
    private static Jws<Claims> parseToken(String token, String passPhrase) {
        return Jwts.parserBuilder()
                .setSigningKey(JWTUtil.getKey(passPhrase))
                .build()
                .parseClaimsJws(token);
    }
}
