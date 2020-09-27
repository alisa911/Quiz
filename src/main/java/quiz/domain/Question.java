package quiz.domain;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "questions")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "question_id_seq")
    private Long id;

    @Column(name = "question")
    private String question;

    @OneToMany(mappedBy = "question", cascade=CascadeType.REMOVE)
    private Set<Answer> answers;
}
