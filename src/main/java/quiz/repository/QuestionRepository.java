package quiz.repository;

import org.springframework.data.jpa.repository.Query;
import quiz.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findByQuestion(String question);

    @Query(nativeQuery = true,
            value = "select * from questions order by random() limit 10")
    List<Question> getRandomTenQuestions();
}
