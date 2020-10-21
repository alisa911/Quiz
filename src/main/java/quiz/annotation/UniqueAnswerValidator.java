package quiz.annotation;

import quiz.controller.request.AnswerRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UniqueAnswerValidator implements ConstraintValidator<UniqueAnswer, List<AnswerRequest>> {

    @Override
    public void initialize(UniqueAnswer uniqueAnswer) {
    }

    @Override
    public boolean isValid(List<AnswerRequest> answers, ConstraintValidatorContext constraintValidatorContext) {

        List<String> values = answers.stream()
                .map(AnswerRequest::getValue)
                .collect(Collectors.toList());

        return values.stream()
                .noneMatch(value -> Collections.frequency(values, value) >1);
    }
}
