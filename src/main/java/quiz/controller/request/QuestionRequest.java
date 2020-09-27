package quiz.controller.request;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class QuestionRequest {

    private String question;
    private Set<AnswerRequest> answers;
}
