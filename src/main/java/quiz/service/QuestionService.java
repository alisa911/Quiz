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

import static quiz.service.util.UtilService.updateAnswers;

@Service
@RequiredArgsConstructor
public class QuestionService implements CrudService<Question> {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    @Override
    public Question create(Question question) {
        List<Answer> answers = question.getAnswers();
        questionRepository.save(question);
        answers.forEach(answer -> answer.setQuestion(question));
        answers.forEach(answerRepository::save);

        return question;
    }

    @Transactional
    @Override
    public void update(Question question, Long questionId) {
        Question questionExists = questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomNotFoundException(questionId));

        question.setId(questionId);
        List<Answer> answersExists = questionExists.getAnswers();
        List<Answer> answersNew = question.getAnswers();

        if (answersNew != null) {
            updateAnswers(answersNew, answersExists, question, answerRepository);
        }

        questionRepository.save(question);
    }

    @Override
    public Question get(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(id));
    }

    public Question getByQuestion(String question) {
        return questionRepository.findByQuestion(question)
                .orElseThrow(() -> new CustomNotFoundException(question));
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public List<Question> getRandomTenQuestions() {
        return questionRepository.getRandomTenQuestions();
    }
}
