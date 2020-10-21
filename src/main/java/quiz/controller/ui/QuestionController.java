package quiz.controller.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import quiz.controller.mapper.QuestionMapper;
import quiz.controller.request.AnswerRequest;
import quiz.controller.request.QuestionRequest;
import quiz.domain.Question;
import quiz.service.QuestionService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/ui/questions")
@Controller("ui-question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("questions", questionService.getAll());
        return "questionList";
    }

    @GetMapping("/save")
    public String showAddQuestionPage(Model model, QuestionRequest questionRequest, AnswerRequest answerRequest) {
        questionRequest.setAnswers(List.of(answerRequest));
        model.addAttribute("questionForm", questionRequest);
        return "addQuestion";
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@Valid @ModelAttribute("questionForm") QuestionRequest questionRequest,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "addQuestion";
        }

        Question question = questionMapper.toQuestion(questionRequest);
        questionService.create(question);
        return "redirect:/ui/questions/all";
    }

    @GetMapping("/delete")
    public String handleDeleteQuestion(@RequestParam(name = "questionId") Long id) {
        questionService.get(id);
        questionService.delete(id);
        return "redirect:/ui/questions/all";
    }

    @GetMapping("/edit")
    public String showUpdateQuestionPage(Model model, @RequestParam(name = "questionId") Long id) {
        Question question = questionService.get(id);
        model.addAttribute("questionForm", question);
        return "updateQuestion";
    }

    @PostMapping(path = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable("id") long id,
                         @Valid @ModelAttribute("questionForm") QuestionRequest questionRequest, BindingResult bindingResult) {

        questionRequest.setId(id);

        if (bindingResult.hasErrors()) {
            return "updateQuestion";
        }

        Question question = questionMapper.toQuestion(questionRequest);
        questionService.update(question, id);
        return "redirect:/ui/questions/all";
    }

}


