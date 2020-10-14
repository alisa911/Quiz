package quiz.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SeveralTrueAnswersException extends RuntimeException {

    public SeveralTrueAnswersException(final String message) {
        super(message);
    }
}
