package quiz.service;

import quiz.domain.Answer;
import quiz.exception.exceptions.CustomNotFoundException;
import quiz.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AnswerService implements CrudService<Answer> {

    private final AnswerRepository answerRepository;

    @Override
    public Answer create(Answer answer) {
        answerRepository.save(answer);
        return answer;
    }

    @Override
    public void update(Answer answer, Long id) {
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
