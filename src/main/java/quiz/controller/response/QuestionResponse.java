package quiz.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class QuestionResponse {

    private Long id;
    private String question;
    private Set<AnswerResponse> answers;
}
