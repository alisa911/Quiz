package quiz.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "questions")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "question_id_seq")
    private Long id;

    @Column(name = "question")
    private String question;

    @OneToMany(mappedBy = "question", cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Answer> answers;
}
