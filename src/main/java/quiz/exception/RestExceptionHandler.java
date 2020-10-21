package quiz.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import quiz.exception.exceptions.*;

import static org.springframework.http.ResponseEntity.notFound;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<Object> entityNotFound(CustomNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return notFound().build();
    }
}
