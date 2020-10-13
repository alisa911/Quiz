package quiz.controller.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quiz.domain.Answer;
import quiz.service.AnswerService;

@RequestMapping("/ui/answers")
@Controller("ui-answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/delete")
    public String handleDeleteQuestion(@RequestParam(name = "answerId") Long id) {
        Answer answer = answerService.get(id);
        Long questionId = answer.getQuestion().getId();
        answerService.delete(id);

        return "redirect:/ui/questions/edit?questionId=" + questionId;
    }
}
