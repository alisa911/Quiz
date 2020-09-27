package quiz.controller.mapper;

import org.mapstruct.Mapping;
import quiz.controller.request.QuestionRequest;
import quiz.controller.response.QuestionResponse;
import quiz.domain.Question;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionResponse toQuestionResponse(Question question);

    @Mapping(target = "question")
    Question toQuestion(QuestionRequest questionRequest);

    List<QuestionResponse> toQuestionResponse(List<Question> questions);
}
