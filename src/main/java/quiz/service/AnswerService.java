package quiz.service;

import quiz.domain.Answer;
import quiz.domain.Question;
import quiz.exception.exceptions.AlreadyExistException;
import quiz.exception.exceptions.CustomNotFoundException;
import quiz.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.repository.QuestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService implements CrudService<Answer> {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Answer create(Answer answer) {
        checkAnswerExists(answer);
        answer.setIsTrue(false);
        answerRepository.save(answer);

        return answer;
    }

    @Override
    public void update(Answer answer, Long id) {
        answerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(id));

        answer.setId(id);
        checkAnswerExists(answer);
        checkTrueAnswerExists(answer);

        answerRepository.save(answer);
    }

    @Override
    public Answer get(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    private void checkAnswerExists(Answer answer) {
        if (getQuestionByAnswer(answer).getAnswers().stream()
                .anyMatch(answerExist -> answerExist.getValue()
                        .equals(answer.getValue()))) {
            throw new AlreadyExistException("Such an answer already exists: " + answer);
        }
    }

    private void checkTrueAnswerExists(Answer answer) {
        if (getQuestionByAnswer(answer).getAnswers().stream()
                .anyMatch(answerExist -> answerExist.getIsTrue()
                        .equals(answer.getIsTrue()) && answerExist.getIsTrue().equals(true))) {
            throw new AlreadyExistException("True answer already exists: " + answer);
        }
    }

    private Question getQuestionByAnswer(Answer answer) {
        Long answerId = answer.getQuestion().getId();
        return questionRepository.findById(answerId).orElseThrow(() -> new CustomNotFoundException(answerId));
    }
}
