package quiz.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerResponse {

    private Long id;
    private String value;
    private Boolean isTrue;
}
