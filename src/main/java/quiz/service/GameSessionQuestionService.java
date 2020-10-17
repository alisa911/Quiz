package quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.domain.GameSessionQuestion;
import quiz.exception.exceptions.CustomNotFoundException;
import quiz.repository.GameSessionQuestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameSessionQuestionService implements CrudService<GameSessionQuestion> {

    private final GameSessionQuestionRepository gameSessionQuestionRepository;

    @Transactional
    @Override
    public GameSessionQuestion create(GameSessionQuestion gameSessionQuestion) {
        return gameSessionQuestionRepository.save(gameSessionQuestion);
    }

    @Transactional
    @Override
    public void update(GameSessionQuestion gameSessionQuestion, Long id) {
        gameSessionQuestion.setId(id);
        gameSessionQuestionRepository.save(gameSessionQuestion);
    }

    @Override
    public GameSessionQuestion get(Long id) {
        return gameSessionQuestionRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        gameSessionQuestionRepository.deleteById(id);
    }

    @Override
    public List<GameSessionQuestion> getAll() {
        return gameSessionQuestionRepository.findAll();
    }
}
