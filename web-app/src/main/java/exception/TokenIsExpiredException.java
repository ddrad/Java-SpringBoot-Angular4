package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Token is expired")
public class TokenIsExpiredException extends RuntimeException {

    public TokenIsExpiredException(String message) {
        super(message);
    }

}
