package quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quiz.controller.mapper.AnswerMapper;
import quiz.controller.request.AnswerRequest;
import quiz.controller.response.AnswerResponse;
import quiz.domain.Answer;
import quiz.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerController.class);

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponse> get(@PathVariable("id") Long id) {
        LOGGER.info("Get answer with id={}", id);

        Answer answer = answerService.get(id);
        AnswerResponse answerResponse = answerMapper.toAnswerResponse(answer);

        return ok(answerResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnswerResponse> delete(@PathVariable("id") Long id) {
        LOGGER.info("Delete answer with id={}", id);

        answerService.get(id);
        answerService.delete(id);

        return noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnswerResponse>> all() {
        LOGGER.info("Get all answers");

        List<Answer> answers = answerService.getAll();
        List<AnswerResponse> answersResponseList = answerMapper.toAnswerResponse(answers);

        return ok(answersResponseList);
    }

    @PostMapping("")
    public ResponseEntity<AnswerResponse> save(@RequestBody AnswerRequest answerRequest, HttpServletRequest request) {
        LOGGER.info("create {}", answerRequest);

        Answer answer = answerMapper.toAnswer(answerRequest);
        AnswerResponse answerResponse = answerMapper.toAnswerResponse(answerService.create(answer));

        return created(
                ServletUriComponentsBuilder
                        .fromContextPath(request)
                        .path("/v1/api/answers/{id}")
                        .buildAndExpand(answerResponse)
                        .toUri())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerResponse> update(@PathVariable("id") Long id, @RequestBody AnswerRequest answerRequest) {
        LOGGER.info("update {} with id={}", answerRequest, id);

        answerService.get(id);
        Answer answer = answerMapper.toAnswer(answerRequest);
        answerService.update(answer, id);

        return noContent().build();
    }
}
