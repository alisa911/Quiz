package quiz.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "answers")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "answers_id_seq")
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "is_true")
    private Boolean isTrue;
}
