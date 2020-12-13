package quiz.controller.mapper;

import quiz.controller.response.AnswerResponse;
import quiz.domain.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerResponse toAnswerResponse(Answer answer);
}
