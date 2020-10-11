package quiz.controller.ui.model;

import lombok.Builder;
import lombok.Data;
import quiz.controller.request.AnswerRequest;

import java.util.Set;

@Data
@Builder
public class QuestionModel {
    private String question;
    private Set<AnswerRequest> answers;
}
