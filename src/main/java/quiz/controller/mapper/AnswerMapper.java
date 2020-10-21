package quiz.controller.mapper;

import org.mapstruct.Mapping;
import quiz.controller.request.AnswerRequest;
import quiz.controller.response.AnswerResponse;
import quiz.domain.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    AnswerResponse toAnswerResponse(Answer answer);

    Answer toAnswer(AnswerRequest answerRequest);

    List<AnswerResponse> toAnswerResponse(List<Answer> answers);
}
