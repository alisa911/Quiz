package quiz.exception.handler;

import quiz.exception.CustomNotFoundException;
import quiz.exception.NoOneTrueAnswerException;
import quiz.exception.SeveralTrueAnswersException;
import quiz.exception.AlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.ResponseEntity.notFound;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<Object> entityNotFound(CustomNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return notFound().build();
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<Object> alreadyExist(AlreadyExistException ex) {
        LOGGER.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(SeveralTrueAnswersException.class)
    public ResponseEntity<Object> severalTrueTranslations(SeveralTrueAnswersException ex) {
        LOGGER.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(NoOneTrueAnswerException.class)
    public ResponseEntity<Object> severalTrueTranslations(NoOneTrueAnswerException ex) {
        LOGGER.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
