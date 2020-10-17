package quiz.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "game_sessions")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "game_id_seq")
    private Long id;

    @Column(name = "start", insertable = false)
    private Instant startTime;

    @Column(name = "score")
    private Long score;

    @OneToMany(mappedBy = "gameSession", cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<GameSessionQuestion> gameSessionQuestions;
}
