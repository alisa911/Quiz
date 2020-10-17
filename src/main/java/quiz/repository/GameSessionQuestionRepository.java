package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quiz.domain.GameSessionQuestion;

public interface GameSessionQuestionRepository extends JpaRepository<GameSessionQuestion, Long> {
}
