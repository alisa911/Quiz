package quiz.service;

import quiz.domain.Answer;
import quiz.domain.Question;
import quiz.exception.exceptions.CustomNotFoundException;
import quiz.exception.exceptions.AlreadyExistException;
import quiz.repository.AnswerRepository;
import quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static quiz.service.util.UtilService.checkAnswerExists;
import static quiz.service.util.UtilService.checkTrueAnswersCount;

@Service
@RequiredArgsConstructor
public class QuestionService implements CrudService<Question> {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    @Override
    public Question create(Question question) {
        List<Answer> answers = question.getAnswers();
        checkQuestionExists(question);
        checkTrueAnswersCount(answers);

        questionRepository.save(question);

        answers.forEach(translation -> translation.setQuestion(question));
        answers.forEach(answerRepository::save);

        return question;
    }

    @Transactional
    @Override
    public void update(Question question, Long questionId) {
        Question questionExists = questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomNotFoundException(questionId));

        if (!questionExists.getQuestion().equals(question.getQuestion())){
            checkQuestionExists(question);
        }

        question.setId(questionId);

        List<Answer> answersExists = questionExists.getAnswers();
        List<Answer> answersNew = question.getAnswers();

        checkTrueAnswersCount(answersNew);
        checkAnswerExists(answersNew, answersExists, question, answerRepository);

        questionRepository.save(question);
    }

    @Override
    public Question get(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    private void checkQuestionExists(Question question) {
        String questionName = question.getQuestion();

        if (questionRepository.findByQuestion(questionName).isPresent()) {
            throw new AlreadyExistException("Такой вопрос уже существует: " + questionName);
        }
    }
}
