package quiz;

import quiz.domain.Answer;
import quiz.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {

    public static Question QUESTION_1 = new Question(null,"Почему?",null);
    public static Question QUESTION_2 = new Question(null,"Где?",null);
    public static Question QUESTION_3 = new Question(null,"Когда?",null);

    public static Answer ANSWER_1 = new Answer(null,"потому что гладиолус",null,true);
    public static Answer ANSWER_2 = new Answer(null,"никогда",null,true);
    public static Answer ANSWER_3 = new Answer(null,"не знаю",null,false);
    public static Answer ANSWER_4 = new Answer(null,"нигде",null,false);
    public static Answer ANSWER_6 = new Answer(null,"что",null,true);

    public static String STRING = "Но как же так?";

    public static Long BIG_ID = 100L;

    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToComparingOnlyGivenFields(expected);
    }
}
