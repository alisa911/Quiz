package quiz.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyInputException extends RuntimeException {

    public EmptyInputException(final String message) {
        super(message);
    }
}
