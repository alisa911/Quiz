package quiz.controller.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest implements Serializable {

    @NotEmpty
    @Length(min = 2, max = 30)
    private String value;

    private Boolean isTrue;
}
