package quiz.controller.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quiz.controller.mapper.QuestionMapper;
import quiz.controller.request.AnswerRequest;
import quiz.controller.request.QuestionRequest;
import quiz.domain.Question;
import quiz.service.QuestionService;

import java.util.List;

@RequestMapping("/ui/questions")
@Controller("ui")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("questions", questionService.getAll());
        return "questionList";
    }

    @GetMapping("")
    public String showAddQuestionPage(Model model) {
        AnswerRequest answerRequest = AnswerRequest.builder().build();
        QuestionRequest questionRequest = QuestionRequest.builder().answers(List.of(answerRequest)).build();

        model.addAttribute("questionForm", questionRequest);
        return "addQuestion";
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@ModelAttribute("questionForm") QuestionRequest questionRequest) {
        Question question = questionMapper.toQuestion(questionRequest);
        questionService.create(question);
        return "redirect:/ui/questions/all";
    }

    @GetMapping("/delete")
    public String handleDeleteUser(@RequestParam(name = "questionId") Long id) {
        questionService.get(id);
        questionService.delete(id);
        return "redirect:/ui/questions/all";
    }
}


