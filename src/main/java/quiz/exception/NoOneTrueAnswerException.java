package quiz.exception;

public class NoOneTrueAnswerException extends RuntimeException {

    public NoOneTrueAnswerException(final String message) {
        super(message);
    }
}
