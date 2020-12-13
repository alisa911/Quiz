package quiz.controller.mapper;

import org.mapstruct.Mapping;
import quiz.controller.request.QuestionRequest;
import quiz.controller.response.QuestionResponse;
import quiz.domain.Question;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionResponse toQuestionResponse(Question question);

    @Mapping(target = "question")
    Question toQuestion(QuestionRequest questionRequest);
}
