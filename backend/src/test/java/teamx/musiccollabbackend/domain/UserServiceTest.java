package teamx.musiccollabbackend.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import teamx.musiccollabbackend.domain.User.IUserRepository;
import teamx.musiccollabbackend.domain.User.UserModel;
import teamx.musiccollabbackend.domain.User.UserService;
import teamx.musiccollabbackend.domain.exceptions.ConflictException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

//    @InjectMocks
//    private UserService userService;
//
//    @Mock
//    private IUserRepository userRepo;
//
//    @Mock
//    private PasswordEncoder encoder;
//
//    @Test
//    public void testCreateValidUser() {
//        // Arrange
//        UserModel newUser = new UserModel("validUser@example.com", "password");
//        when(encoder.encode(newUser.getPassword())).thenReturn("encodedPassword");
//
//        // Act
//        UserModel createdUser = userService.create(newUser);
//
//        // Assert
//        assertNotNull(createdUser);
//        assertEquals("validUser@example.com", createdUser.getEmail());
//        assertEquals("encodedPassword", createdUser.getPassword());
//        verify(userRepo, times(1)).save(createdUser);
//    }
//
//    @Test
//    public void testCreateInvalidEmail() {
//        // Arrange
//        UserModel invalidEmailUser = new UserModel("invalidEmail", "password");
//
//        // Act and Assert
//        assertThrows(ConflictException.class, () -> userService.create(invalidEmailUser));
//        verify(userRepo, never()).save(any(UserModel.class));
//    }
//
//    @Test
//    public void testCreateSmallEmail() {
//        // Arrange
//        UserModel smallEmailUser = new UserModel("a@b", "password");
//
//        // Act and Assert
//        assertThrows(ConflictException.class, () -> userService.create(smallEmailUser));
//        verify(userRepo, never()).save(any(UserModel.class));
//    }
}
