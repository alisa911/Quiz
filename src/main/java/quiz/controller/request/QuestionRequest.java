package quiz.controller.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {

    private String question;
    private List<AnswerRequest> answers;
}
