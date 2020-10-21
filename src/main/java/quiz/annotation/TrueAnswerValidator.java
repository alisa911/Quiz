package quiz.annotation;

import quiz.controller.request.AnswerRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TrueAnswerValidator implements ConstraintValidator<OneTrueAnswer, List<AnswerRequest>> {

    @Override
    public void initialize(OneTrueAnswer oneTrueAnswer) {
    }

    @Override
    public boolean isValid(List<AnswerRequest> answers, ConstraintValidatorContext constraintValidatorContext) {
        int trueAnswersSize = (int) answers.stream()
                .filter(AnswerRequest::getIsTrue).count();
        return trueAnswersSize == 1;
    }
}
