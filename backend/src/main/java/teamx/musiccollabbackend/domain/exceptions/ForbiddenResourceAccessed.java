package teamx.musiccollabbackend.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Runtime exception, that responds with HTTP error code 403 (FORBIDDEN).
 *
 * @author Hidde Balestra
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenResourceAccessed extends RuntimeException {

    public ForbiddenResourceAccessed(String message) {
        super(message);
    }

}
