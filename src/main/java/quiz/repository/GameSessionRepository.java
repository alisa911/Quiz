package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quiz.domain.GameSession;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
}
