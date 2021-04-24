package orange.talent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    public BadRequestException(BadRequestException badRequestException, String message) {
        super(message);
    }

    public BadRequestException(String s) {
        super(s);
    }
}
