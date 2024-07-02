package teamx.musiccollabbackend.domain.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import teamx.musiccollabbackend.domain.exceptions.ConflictException;

import static org.springframework.util.StringUtils.hasLength;

/**
 * A service to perform operations on UserRoles.
 *
 * @author Hidde Balestra
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final IUserRepository userRepo;
    @Autowired
    private final PasswordEncoder encoder;

    public UserModel findByEmail(String email) {
        if (!hasLength(email)) return null;

        return userRepo.findUserByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = findByEmail(email);

        if (user == null) throw new UsernameNotFoundException(
                String.format("User with email - %s, not found", email));

        return user;
    }

    public UserModel create(UserModel req) {
        if (!req.getEmail().contains("@") || !req.getEmail().contains(".")){
            throw new ConflictException("Email is not valid.");
        }
        if (req.getEmail().length() < 5){
            throw new ConflictException("Email is to small to be valid.");
        }

        var user = new UserModel();

        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));

        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setContactInfo(req.getContactInfo());
        user.setOccupation(req.getOccupation());
        user.setCountry(req.getCountry());

        user.setDescription(req.getDescription());
        user.setAccountType(req.getAccountType());
        user.setActive(req.getActive());

        this.userRepo.save(user);

        return user;
    }

}
