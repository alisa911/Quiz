package quiz.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import quiz.controller.request.AnswerRequest;
import quiz.controller.request.QuestionRequest;
import quiz.domain.Answer;
import quiz.domain.Question;
import quiz.exception.exceptions.*;
import quiz.repository.AnswerRepository;
import quiz.repository.QuestionRepository;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilService {

    public static Question questionUpdate;

    public static void checkTrueAnswersCount(List<Answer> answers) {
        int trueAnswersSize = (int) answers.stream()
                .filter(Answer::getIsTrue).count();

        if (trueAnswersSize > 1) {
            throw new SeveralTrueAnswersException("Можно добавить только один правильный ответ");
        }

        if (trueAnswersSize == 0) {
            throw new NoOneTrueAnswerException("Добавь хотя бы один правильный ответ");
        }
    }

    public static void checkAnswerExists(List<Answer> answersNew, List<Answer> answersExists,
                                         Question question, AnswerRepository answerRepository) {

        for (Answer answerExist : answersExists) {
            if (answersNew != null) {
                for (Answer answerNew : answersNew) {
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
                }
            }
        }
    }

    public static void checkAnswerExists(Answer answer, List<Answer> answersExists) {
        if (answersExists.stream()
                .anyMatch(answerExists -> answerExists.getValue().equals(answer.getValue()))) {
            throw new AlreadyExistException("Such an answer already exists: " + answer.getValue());
        }
    }

    public static Question getQuestionByAnswer(Answer answer, QuestionRepository questionRepository) {
        Long questionId = answer.getQuestion().getId();
        return questionRepository.findById(questionId).orElseThrow(() -> new CustomNotFoundException(questionId));
    }

    public static void createQuestionForm(Model model) {
        AnswerRequest answerRequest = AnswerRequest.builder().build();
        QuestionRequest questionRequest = QuestionRequest.builder().answers(List.of(answerRequest)).build();
        model.addAttribute("questionForm", questionRequest);
    }

    public static void updateQuestionForm(Model model) {
        model.addAttribute("questionForm", questionUpdate);
    }

    public static ModelAndView createQuestionFormWithError(Model model, Exception ex) {
        createQuestionForm(model);
        model.addAttribute("error", ex.getMessage());
        return new ModelAndView("addQuestion");
    }

    public static ModelAndView updateQuestionFormWithError(Model model, Exception ex) {
        updateQuestionForm(model);
        model.addAttribute("error", ex.getMessage());
        return new ModelAndView("updateQuestion");
    }

    public static void checkEmptyInput(Question question) {
        String questionValue = question.getQuestion();
        List<Answer> answers = question.getAnswers();

        if (questionValue.equals("") || answers.stream().anyMatch(answer -> answer.getValue().equals(""))) {
            throw new EmptyInputException("Заполните все поля ввода");
        }
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
