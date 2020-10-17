package quiz.controller.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quiz.domain.Answer;
import quiz.domain.GameSession;
import quiz.domain.GameSessionQuestion;
import quiz.domain.Question;
import quiz.service.AnswerService;
import quiz.service.GameSessionQuestionService;
import quiz.service.GameSessionService;
import quiz.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/ui/game")
@Controller("ui-game")
@RequiredArgsConstructor
public class GameSessionController {

    private final QuestionService questionService;
    private final GameSessionService gameSessionService;
    private final GameSessionQuestionService gameSessionQuestionService;
    private final AnswerService answerService;


    @GetMapping("/play")
    public String startGame(Model model) {

        List<Question> questions = new ArrayList<>(questionService.getRandomTenQuestions());

        GameSession gameSession = gameSessionService.create(GameSession.builder().build());

        Long gameSessionId = gameSession.getId();

        List<GameSessionQuestion> gameSessionQuestionList = questions.stream()
                .map(question -> gameSessionQuestionService.create(GameSessionQuestion.builder()
                        .question(question)
                        .gameSession(gameSession)
                        .build()))
                .collect(Collectors.toList());

        gameSession.setGameSessionQuestions(gameSessionQuestionList);

        GameSessionQuestion gameSessionQuestion = gameSession.getGameSessionQuestions().get(0);
        Long questionId = gameSessionQuestion.getId();

        Question question = gameSessionQuestion.getQuestion();

        model.addAttribute("question", question);
        model.addAttribute("gameId", gameSessionId);
        model.addAttribute("questionId", questionId);

        return "play";
    }

    @PostMapping(path = "/play/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String handleAnswer(@PathVariable("gameId") Long gameId,
                               @RequestParam("questionId") Long questionId,
                               @RequestParam("submit-answer") Long answerId,
                               @ModelAttribute("question") Question question,
                               Model model) {

        Answer answerSelected = answerService.get(answerId);
        GameSessionQuestion questionAnswered = gameSessionQuestionService.get(questionId);
        questionAnswered.setAnswer(answerSelected);
        gameSessionQuestionService.update(questionAnswered, questionId);

        GameSession currentGame = gameSessionService.get(gameId);

        GameSessionQuestion nextQuestion = currentGame.getGameSessionQuestions().stream()
                .filter(gameSessionQuestion -> gameSessionQuestion.getAnswer() == null)
                .findFirst()
                .orElse(null);

        if (nextQuestion != null) {
            questionId = nextQuestion.getId();
            Question newQuestion = nextQuestion.getQuestion();

            model.addAttribute("question", newQuestion);
            model.addAttribute("gameId", gameId);
            model.addAttribute("questionId", questionId);
            return "play";
        } else
            return "redirect:/ui/game/finish/" + +gameId;
    }

    @GetMapping("/finish/{gameId}")
    public String showResults(@PathVariable("gameId") Long gameId,
                              Model model) {

        GameSession gameFinished = gameSessionService.get(gameId);

        long score = gameFinished.getGameSessionQuestions().stream()
                .filter(gameSessionQuestion -> gameSessionQuestion.getAnswer().getIsTrue().equals(Boolean.TRUE))
                .count();

        gameFinished.setScore(score);
        gameSessionService.update(gameFinished, gameId);

        model.addAttribute("score", score);
        model.addAttribute("gameId", gameId);

        return "finish";
    }
}
