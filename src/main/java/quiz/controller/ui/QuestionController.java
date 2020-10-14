package quiz.controller.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quiz.controller.mapper.QuestionMapper;
import quiz.controller.request.QuestionRequest;
import quiz.domain.Question;
import quiz.service.QuestionService;

import static quiz.service.util.UtilService.*;

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

    @GetMapping("")
    public String showAddQuestionPage(Model model) {
        createQuestionForm(model);
        return "addQuestion";
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@ModelAttribute("questionForm") QuestionRequest questionRequest) {
        Question question = questionMapper.toQuestion(questionRequest);
        checkEmptyInput(question);
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
        questionExist = question;
        model.addAttribute("questionForm", question);
        return "updateQuestion";
    }

    @PostMapping(path = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@PathVariable("id") long id, @ModelAttribute("questionForm") QuestionRequest questionRequest) {
        Question question = questionMapper.toQuestion(questionRequest);
        checkEmptyInput(question);
        questionService.update(question, id);

        return "redirect:/ui/questions/all";
    }

}


