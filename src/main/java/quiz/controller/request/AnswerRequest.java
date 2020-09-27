package quiz.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerRequest {

    private String value;
    private Boolean isTrue;
    private Long questionId;
}
