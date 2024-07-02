package teamx.musiccollabbackend.domain.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import teamx.musiccollabbackend.domain.User.UserModel;

import java.util.Optional;

public class SecurityContextHelper {
    public static Optional<UserModel> getCurrentlyLoggedInUser() {

        var currentAuth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(currentAuth);

        if (currentAuth == null) return Optional.empty();

        if (currentAuth.getPrincipal() == "anonymousUser"){
            return Optional.empty();
        }
        return Optional.of(((UserModel) currentAuth.getPrincipal()));
    }
}
