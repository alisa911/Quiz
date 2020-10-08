package quiz.service;

import quiz.domain.Answer;
import quiz.domain.Question;
import quiz.exception.exceptions.CustomNotFoundException;
import quiz.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quiz.repository.QuestionRepository;

import java.util.List;
import java.util.Set;

import static quiz.service.util.UtilService.*;

@Service
@RequiredArgsConstructor
public class AnswerService implements CrudService<Answer> {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Answer create(Answer answer) {
        Question question = getQuestionByAnswer(answer, questionRepository);
        Set<Answer> answersExists = question.getAnswers();
        checkAnswerExists(answer, answersExists);

        answersExists.add(answer);
        checkTrueAnswersCount(answersExists);

        answerRepository.save(answer);
        return answer;
    }

    @Override
    public void update(Answer answer, Long id) {
        Answer answerExist = answerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(id));
        Set<Answer> answersExists = answerExist.getQuestion().getAnswers();
        checkAnswerExists(answer, answersExists);

        answersExists.remove(answerExist);
        answersExists.add(answer);
        checkTrueAnswersCount(answersExists);

        answer.setId(id);
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

}
