package quiz.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import quiz.exception.exceptions.*;

import static org.springframework.http.ResponseEntity.notFound;
import static quiz.service.util.UtilService.createQuestionFormWithError;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<Object> entityNotFound(CustomNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return notFound().build();
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ModelAndView alreadyExist(AlreadyExistException ex, Model model) {
        LOGGER.error(ex.getMessage());
        return createQuestionFormWithError(model, ex);
    }

    @ExceptionHandler({SeveralTrueAnswersException.class, NoOneTrueAnswerException.class})
    public ModelAndView countTrueAnswers(Exception ex, Model model) {
        LOGGER.error(ex.getMessage());
        return createQuestionFormWithError(model, ex);
    }

    @ExceptionHandler(EmptyInputException.class)
    public ModelAndView inputEmpty(EmptyInputException ex, Model model) {
        LOGGER.error(ex.getMessage());
        return createQuestionFormWithError(model, ex);
    }
}
