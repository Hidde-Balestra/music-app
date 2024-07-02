package teamx.musiccollabbackend.domain.utils.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * An implementation of a JPA Auditor. It provides the name of the currently
 * authenticated user making changes. So JPA can make sure that annotations,
 * such as @LastModifiedDate and more function.
 *
 * @author Hidde Balestra
 */
public class CustomAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String userName =
                SecurityContextHolder.getContext().getAuthentication() != null
                        ? SecurityContextHolder.getContext().getAuthentication().getName()
                        : "Not signed in!";

        return Optional.of(userName);
    }
}
