package quiz.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import quiz.exception.exceptions.*;

import java.util.Arrays;

import static org.springframework.http.ResponseEntity.notFound;
import static quiz.service.util.UtilService.createQuestionFormWithError;
import static quiz.service.util.UtilService.updateQuestionFormWithError;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<Object> entityNotFound(CustomNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return notFound().build();
    }

    @ExceptionHandler({AlreadyExistException.class, EmptyInputException.class,
            SeveralTrueAnswersException.class, NoOneTrueAnswerException.class})
    public ModelAndView handleQuestionException(Exception ex, Model model) {
        LOGGER.error(ex.getMessage());
        if (Arrays.toString(ex.getStackTrace()).contains("save")) {
            return createQuestionFormWithError(model, ex);
        } else {
            return updateQuestionFormWithError(model, ex);
        }
    }
}
