package quiz.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "game_session_question")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "game_session_question_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_session_id")
    private GameSession gameSession;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToOne
    @JoinColumn(name = "selected_answer_id")
    private Answer answer;
}
