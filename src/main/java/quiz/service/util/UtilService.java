package quiz.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import quiz.domain.Answer;
import quiz.domain.Question;
import quiz.repository.AnswerRepository;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilService {

    public static void updateAnswers(List<Answer> answersNew, List<Answer> answersExists,
                                     Question question, AnswerRepository answerRepository) {

        answersExists.forEach(answerExist ->
                answersNew.forEach(answerNew -> {
                    if (checkDeleteOldAnswer(answersNew, answerExist)) {
                        answerRepository.delete(answerExist);
                    } else if (checkEqualAnswers(answerNew, answerExist)) {
                        answerNew.setId(answerExist.getId());
                        answerNew.setQuestion(question);
                        answerRepository.save(answerNew);
                    } else if (checkDetectNewAnswer(answerNew, answersExists)) {
                        answerNew.setQuestion(question);
                        answerRepository.save(answerNew);
                    }
                }));
    }

    private static boolean checkDeleteOldAnswer(List<Answer> answersNew, Answer answerExist) {
        return answersNew.stream()
                .noneMatch(answer -> answer.getValue()
                        .equals(answerExist.getValue()));
    }

    private static boolean checkEqualAnswers(Answer answerNew, Answer answerExist) {
        return answerNew.getValue().equals(answerExist.getValue());
    }

    private static boolean checkDetectNewAnswer(Answer answerNew, List<Answer> answersExists) {
        return answersExists.stream()
                .noneMatch(answer -> answer.getValue()
                        .equals(answerNew.getValue()));
    }
}
