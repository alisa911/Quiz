package quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quiz.domain.GameSession;
import quiz.exception.exceptions.CustomNotFoundException;
import quiz.repository.GameSessionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameSessionService implements CrudService<GameSession> {

    private final GameSessionRepository gameSessionRepository;

    @Transactional
    @Override
    public GameSession create(GameSession gameSession) {

        return gameSessionRepository.save(gameSession);
    }

    @Transactional
    @Override
    public void update(GameSession gameSession, Long id) {
        gameSession.setId(id);
        gameSessionRepository.save(gameSession);
    }

    @Override
    public GameSession get(Long id) {
        return gameSessionRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        gameSessionRepository.deleteById(id);
    }

    @Override
    public List<GameSession> getAll() {
        return gameSessionRepository.findAll();
    }
}
