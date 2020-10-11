package quiz.controller.rest;

import quiz.controller.mapper.QuestionMapper;
import quiz.controller.request.QuestionRequest;
import quiz.controller.response.QuestionResponse;
import quiz.domain.Question;
import quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController("rest")
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> get(@PathVariable("id") Long id) {
        LOGGER.info("Get question with id={}", id);

        Question question = questionService.get(id);
        QuestionResponse questionResponse = questionMapper.toQuestionResponse(question);

        return ok(questionResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionResponse> delete(@PathVariable("id") Long id) {
        LOGGER.info("Delete question with id={}", id);

        questionService.get(id);
        questionService.delete(id);

        return noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestionResponse>> all() {
        LOGGER.info("Get all questions");

        List<Question> questions = questionService.getAll();
        List<QuestionResponse> questionsResponseList = questionMapper.toQuestionResponse(questions);

        return ok(questionsResponseList);
    }

    @PostMapping("")
    public ResponseEntity<QuestionResponse> save(@RequestBody QuestionRequest questionRequest, HttpServletRequest request) {
        LOGGER.info("create {}", questionRequest);

        Question question = questionMapper.toQuestion(questionRequest);
        QuestionResponse questionResponse = questionMapper.toQuestionResponse(questionService.create(question));

        return created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/questions/{id}")
                        .buildAndExpand(questionResponse)
                        .toUri())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> update(@PathVariable("id") Long wordId, @RequestBody QuestionRequest questionRequest) {
        LOGGER.info("update {} with id={}", questionRequest, wordId);

        questionService.get(wordId);
        Question question = questionMapper.toQuestion(questionRequest);
        questionService.update(question, wordId);

        return noContent().build();
    }
}
