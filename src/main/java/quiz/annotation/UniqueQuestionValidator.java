package quiz.annotation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import quiz.controller.request.QuestionRequest;
import quiz.repository.QuestionRepository;
import quiz.service.QuestionService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UniqueQuestionValidator implements ConstraintValidator<UniqueQuestion, QuestionRequest> {

    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    @Override
    public void initialize(UniqueQuestion uniqueQuestion) {
    }

    @Override
    public boolean isValid(QuestionRequest question, ConstraintValidatorContext constraintValidatorContext) {
        String questionValue = question.getQuestion();
        boolean isFoundQuestion = questionRepository.findByQuestion(questionValue).isEmpty();

        if (isFoundQuestion) {
            return true;
        } else {
            Long questionExistId = questionService.getByQuestion(questionValue).getId();
            Long questionRequestId = question.getId();
            return questionExistId.equals(questionRequestId);
        }
    }
}
