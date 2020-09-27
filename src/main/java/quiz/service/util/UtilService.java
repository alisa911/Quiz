package quiz.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import quiz.domain.Answer;
import quiz.exception.NoOneTrueAnswerException;
import quiz.exception.SeveralTrueAnswersException;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilService {

    public static void checkTrueAnswersCount(Set<Answer> answers) {
        int trueAnswersSize = (int) answers.stream()
                .filter(Answer::getIsTrue).count();

        if (trueAnswersSize > 1) {
            throw new SeveralTrueAnswersException("You can add only one true answer");
        }

        if (trueAnswersSize == 0) {
            throw new NoOneTrueAnswerException("Add at least one true answer");
        }
    }
}
