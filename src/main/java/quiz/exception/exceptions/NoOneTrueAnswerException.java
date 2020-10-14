package quiz.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NoOneTrueAnswerException extends RuntimeException {

    public NoOneTrueAnswerException(final String message) {
        super(message);
    }
}
