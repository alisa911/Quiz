package quiz.exception;

public class SeveralTrueAnswersException extends RuntimeException {

    public SeveralTrueAnswersException(final String message) {
        super(message);
    }
}
