package teamx.musiccollabbackend.domain.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamx.musiccollabbackend.domain.Label.ILabelRepository;
import teamx.musiccollabbackend.domain.Label.LabelModel;
import teamx.musiccollabbackend.domain.dto.user.LinkLabelsToUserRequest;
import teamx.musiccollabbackend.domain.dto.user.LogInRequest;
import teamx.musiccollabbackend.domain.exceptions.UnauthorizedException;
import teamx.musiccollabbackend.domain.utils.JWTUtil;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static teamx.musiccollabbackend.domain.utils.JWTUtil.JWT_AUTHORIZATION_TOKEN_PREFACE;

/**
 * End points for the authentication of a user. It provides a JWT, if he logs in or registers an account successfully.
 *
 * @author Hidde Balestra
 */
@RequiredArgsConstructor
@RestController
public class UserController {
    private final AuthenticationManager authenticator;
    @Autowired
    IUserRepository repo;

    @Autowired
    ILabelRepository labelRepo;

    private final UserService userService;

    private final JWTUtil tokenHelper;

    /**
     * Checks whether the provided credentials are valid, and authenticates the user, by returning a JWT.
     *
     * @param credentials An object containing the username and password.
     * @return The user that belongs to the given credentials and a JWT for use in authentication.
     */

    @PostMapping("/open/login")
    public ResponseEntity<UserModel> login(@RequestBody @Valid LogInRequest credentials) {

        Authentication authenticate;

        try {
            authenticate = authenticator.authenticate(credentials.toAuthToken());
        } catch (BadCredentialsException ex) {
            throw new UnauthorizedException("Email and/or password is not valid.");
        }

        UserModel user = (UserModel) authenticate.getPrincipal();

        // Check if the user is active
        if (user.getActive() != 1) {
            System.out.println("user " + user.email + " not allowed to login - inactive account");
            throw new UnauthorizedException("Login not allowed for inactive users.");
        }

        return ResponseEntity.accepted()
                .header(ACCESS_CONTROL_EXPOSE_HEADERS, AUTHORIZATION)
                .header(AUTHORIZATION,
                        JWT_AUTHORIZATION_TOKEN_PREFACE + tokenHelper.generateTokenFor(user))
                .body(user);
    }


    @GetMapping(value = "/open/is_available")
    public boolean isAvailable(String email){

        Optional<UserModel> user = repo.findUserByEmail(email);

        return user.isEmpty();
    }

    @PostMapping(value = "/open/register_account")
    public Integer registerAccount(@RequestBody UserModel req) {
        req.setActive(1);

        UserModel user = userService.create(req);
        System.out.println(user.toString());

        return user.getIdUser();
    }

    // TODO: add security, right now every user can add labels to every account

    @PostMapping(value = "/link-labels-to-user")
    public boolean linkLabelsToAccount(@RequestBody LinkLabelsToUserRequest request){
        System.out.println("linkLabelsToAccount");
        try {

            UserModel user = repo.findById(request.getUserId());
            List<LabelModel> labels = labelRepo.findByIds(request.getLabelIds());

            user.getLabels().addAll(labels);

            repo.save(user);

            return true;

        } catch (Exception e){
            return false;
        }
    }

}
