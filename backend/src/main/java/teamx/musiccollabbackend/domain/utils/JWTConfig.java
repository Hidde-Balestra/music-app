package teamx.musiccollabbackend.domain.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JWTConfig {
    @Value("${jwt.issuer:MyOrganisation}")
    private String issuer;

    @Value("${jwt.pass-phrase}")
    private String passPhrase;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String getIssuer() {
        return issuer;
    }
}