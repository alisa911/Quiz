package quiz.controller.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import quiz.annotation.OneTrueAnswer;
import quiz.annotation.UniqueAnswer;
import quiz.annotation.UniqueQuestion;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@UniqueQuestion
public class QuestionRequest implements Serializable {

    long id;

    @NotEmpty
    @Length(min = 5, max = 60)
    private String question;

    @OneTrueAnswer
    @UniqueAnswer
    @Valid
    private List<AnswerRequest> answers;
}
