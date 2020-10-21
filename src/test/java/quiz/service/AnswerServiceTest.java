package quiz.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import quiz.domain.Answer;
import quiz.exception.exceptions.AlreadyExistException;
import quiz.exception.exceptions.CustomNotFoundException;
import quiz.repository.AnswerRepository;
import quiz.repository.QuestionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static quiz.TestData.*;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active:test")
class AnswerServiceTest {


    private final AnswerService answerService;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    Long answerId;

    @BeforeEach
    void setUp() {
        questionRepository.save(QUESTION_1);
        ANSWER_2.setQuestion(QUESTION_1);
        ANSWER_3.setQuestion(QUESTION_1);
        answerRepository.save(ANSWER_2);
        answerRepository.save(ANSWER_3);
        answerId = ANSWER_2.getId();
    }

    @AfterEach
    public void cleanUpEach(){
        answerRepository.deleteAll();
        ANSWER_2.setId(null);
        ANSWER_3.setId(null);
    }

    //get
    @Test
    void get(){
        Answer actualAnswer = answerService.get(answerId);
        assertMatch(actualAnswer, ANSWER_2);
    }

    @Test
    void getNotFound() {
        assertThrows(CustomNotFoundException.class, () ->
                answerService.get(BIG_ID));
    }

    @Test
    void getAll() {
        List<Answer> expectedAnswers = List.of(ANSWER_2, ANSWER_3);
        List<Answer> actualAnswers = answerService.getAll();

        assertMatch(actualAnswers, expectedAnswers);
    }

    //delete
    @Test
    void delete() {
        answerService.delete(answerId);
        List<Answer> expectedAnswers = List.of(ANSWER_3);
        List<Answer> actualAnswers = answerService.getAll();

        assertMatch(actualAnswers, expectedAnswers);
    }

    //create
    @Test
    void create() {
        ANSWER_4.setQuestion(QUESTION_1);
        answerService.create(ANSWER_4);
        List<Answer> expectedAnswers = List.of(ANSWER_2, ANSWER_3, ANSWER_4);
        List<Answer> actualAnswers = answerService.getAll();

        assertMatch(actualAnswers, expectedAnswers);
    }

    //update
    @Test
    void update() {
        Answer actualAnswer = answerService.get(answerId);
        ANSWER_2.setValue(STRING);
        answerService.update(ANSWER_2, answerId);

        assertMatch(actualAnswer, ANSWER_2);
    }
}
