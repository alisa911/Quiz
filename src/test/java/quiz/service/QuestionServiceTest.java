package quiz.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import quiz.domain.Question;
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
class QuestionServiceTest {

    private final QuestionService questionService;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    Long questionId;

    @BeforeEach
    void setUp() {
        questionRepository.save(QUESTION_1);
        ANSWER_2.setQuestion(QUESTION_1);
        answerRepository.save(ANSWER_2);
        questionRepository.save(QUESTION_2);
        questionId = QUESTION_1.getId();
    }

    //get
    @Test
    void get() {
        Question actualQuestion = questionService.get(questionId);
        assertMatch(actualQuestion, QUESTION_1);
    }

    @Test
    void getNotFound() {
        assertThrows(CustomNotFoundException.class, () ->
                questionService.get(BIG_ID));
    }

    @Test
    void getAll() {
        List<Question> expectedQuestions = List.of(QUESTION_1, QUESTION_2);
        List<Question> actualQuestions = questionService.getAll();

        assertMatch(actualQuestions, expectedQuestions);
    }

    //delete
    @Test
    void delete() {
        questionService.delete(questionId);
        List<Question> expectedQuestions = List.of(QUESTION_2);
        List<Question> actualQuestions = questionService.getAll();

        assertMatch(actualQuestions, expectedQuestions);
    }

    //create
    @Test
    void create() {
        QUESTION_3.setAnswers(List.of(ANSWER_1));
        questionService.create(QUESTION_3);
        List<Question> expectedQuestions = List.of(QUESTION_1, QUESTION_2, QUESTION_3);
        List<Question> actualQuestions = questionService.getAll();

        assertMatch(actualQuestions, expectedQuestions);
    }

    //update
    @Test
    void update() {
        Question actualQuestion = questionService.get(questionId);
        QUESTION_1.setQuestion(STRING);
        questionService.update(QUESTION_1, questionId);

        assertMatch(actualQuestion, QUESTION_1);
    }

    @Test
    void updateNotFound() {
        assertThrows(CustomNotFoundException.class, () ->
                questionService.update(QUESTION_1, BIG_ID));
    }

    @AfterEach
    public void cleanUpEach(){
        questionRepository.deleteAll();
        QUESTION_1.setId(null);
    }
}