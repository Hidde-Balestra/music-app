package teamx.musiccollabbackend.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import teamx.musiccollabbackend.domain.User.UserController;
import teamx.musiccollabbackend.domain.User.UserModel;
import teamx.musiccollabbackend.domain.dto.user.LogInRequest;
import teamx.musiccollabbackend.domain.exceptions.UnauthorizedException;
import teamx.musiccollabbackend.domain.utils.JWTUtil;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {
//    @InjectMocks
//    private UserController authenticationController;
//
//    @Mock
//    private AuthenticationManager authenticator;
//
//    @Mock
//    private JWTUtil tokenHelper;
//
//    @Test
//    public void testLoginSuccess() {
//        // Arrange
//        LogInRequest validCredentials = new LogInRequest("validUsername", "validPassword");
//        Authentication authentication = mock(Authentication.class);
//        UserModel user = new UserModel("validUsername");
//        when(authenticator.authenticate(any())).thenReturn(authentication);
//        when(authentication.getPrincipal()).thenReturn(user);
//        when(tokenHelper.generateTokenFor(user)).thenReturn("validToken");
//
//        // Act
//        ResponseEntity<UserModel> response = authenticationController.login(validCredentials);
//
//        // Assert
//        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//        assertEquals(user, response.getBody());
//        assertTrue(response.getHeaders().containsKey(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS));
//        assertTrue(response.getHeaders().containsKey(HttpHeaders.AUTHORIZATION));
//        assertEquals("Bearer validToken", Objects.requireNonNull(response.getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0));
//    }
//
//    @Test
//    public void testLoginFailure() {
//        // Arrange
//        LogInRequest invalidCredentials = new LogInRequest("invalidUsername", "invalidPassword");
//        when(authenticator.authenticate(any())).thenThrow(new BadCredentialsException("Invalid credentials"));
//
//        // Act and Assert
//        assertThrows(UnauthorizedException.class, () -> authenticationController.login(invalidCredentials));
//    }
}
