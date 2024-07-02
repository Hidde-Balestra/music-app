package teamx.musiccollabbackend.domain.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import teamx.musiccollabbackend.domain.User.UserService;

import java.io.IOException;
import java.util.Collection;

import static java.util.Objects.requireNonNullElse;
import static org.springframework.util.StringUtils.hasLength;
import static teamx.musiccollabbackend.domain.utils.JWTUtil.JWT_AUTHORIZATION_TOKEN_PREFACE;

/**
 * A filter to capture JWT tokens embedded in HTTP Authorizations header from incoming requests.
 * If a JWT is present it validates it and authenticates the user.
 *
 * @author Hidde Balestra
 */
@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JWTUtil tokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain
    ) throws
            ServletException,
            IOException {

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);

        // Check if a JWT is not present in the header, and pass to the next filter.
        if (!hasLength(header) || !header.startsWith(JWT_AUTHORIZATION_TOKEN_PREFACE)) {
            chain.doFilter(req, res);
            return;
        }

        String token = header.replace(JWT_AUTHORIZATION_TOKEN_PREFACE, "").trim();

        // Check if the provided JWT token is invalid, and pass to the next filter.
        if (!tokenUtil.validate(token)) {
            chain.doFilter(req, res);
            return;
        }

        // Assign the user to the spring security context, and pass to the next filter.
        var user = userService.findByEmail(tokenUtil.getEmailOf(token));

        if (user == null) SecurityContextHolder.getContext().setAuthentication(null);
        else {
            var authToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    (Collection<? extends GrantedAuthority>) requireNonNullElse(user.getAccountType(), ""));

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        chain.doFilter(req, res);
    }
}
