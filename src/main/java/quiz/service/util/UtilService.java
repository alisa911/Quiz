package quiz.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import quiz.domain.Answer;
import quiz.domain.Question;
import quiz.exception.exceptions.AlreadyExistException;
import quiz.exception.exceptions.CustomNotFoundException;
import quiz.exception.exceptions.NoOneTrueAnswerException;
import quiz.exception.exceptions.SeveralTrueAnswersException;
import quiz.repository.AnswerRepository;
import quiz.repository.QuestionRepository;

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

    public static void checkAnswerExists(Set<Answer> answersNew, Set<Answer> answersExists,
                                         Question question, AnswerRepository answerRepository) {

        for (Answer answerExist : answersExists) {
            if (answersNew != null) {
                for (Answer answerNew : answersNew) {
                    if (answersNew.stream()
                            .noneMatch(answer -> answer.getValue()
                                    .equals(answerExist.getValue()))) {
                        answerRepository.delete(answerExist);
                    } else if (answerNew.getValue().equals(answerExist.getValue())) {
                        answerNew.setId(answerExist.getId());
                        answerNew.setQuestion(question);
                        answerRepository.save(answerNew);
                    } else if (answersExists.stream()
                            .noneMatch(answer -> answer.getValue()
                                    .equals(answerNew.getValue()))) {
                        answerNew.setQuestion(question);
                        answerRepository.save(answerNew);
                    }
                }
            }
        }
    }

    public static void checkAnswerExists(Answer answer, Set<Answer> answersExists) {
        if (answersExists.stream()
                .anyMatch(answerExists -> answerExists.getValue().equals(answer.getValue()))) {
            throw new AlreadyExistException("Such an answer already exists: " + answer.getValue());
        }
    }

    public static Question getQuestionByAnswer(Answer answer, QuestionRepository questionRepository) {
        Long questionId = answer.getQuestion().getId();
        return questionRepository.findById(questionId).orElseThrow(() -> new CustomNotFoundException(questionId));
    }
}
