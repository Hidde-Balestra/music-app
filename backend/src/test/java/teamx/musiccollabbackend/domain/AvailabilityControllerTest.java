package teamx.musiccollabbackend.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import teamx.musiccollabbackend.domain.User.IUserRepository;
import teamx.musiccollabbackend.domain.User.UserController;
import teamx.musiccollabbackend.domain.User.UserModel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AvailabilityControllerTest {

//    @InjectMocks
//    private UserController availabilityController;
//
//    @Mock
//    private IUserRepository repo;
//
//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testIsAvailableTrue() {
//        // Arrange
//        String availableEmail = "available@example.com";
//        when(repo.findUserByEmail(availableEmail)).thenReturn(Optional.empty());
//
//        // Act
//        boolean isAvailable = availabilityController.isAvailable(availableEmail);
//
//        // Assert
//        assertTrue(isAvailable);
//    }
//
//    @Test
//    public void testIsAvailableFalse() {
//        // Arrange
//        String takenEmail = "oegetester@gmail.com";
//        UserModel existingUser = new UserModel(takenEmail);
//        when(repo.findUserByEmail(takenEmail)).thenReturn(Optional.of(existingUser));
//
//        // Act
//        boolean isAvailable = availabilityController.isAvailable(takenEmail);
//
//        // Assert
//        assertFalse(isAvailable);
//    }
}
