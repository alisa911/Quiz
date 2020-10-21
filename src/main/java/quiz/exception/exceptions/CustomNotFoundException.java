package quiz.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException(Long id) {
        super("Not found with id " + id);
    }

    public CustomNotFoundException(String question) {
        super("Not found with id " + question);
    }
}
