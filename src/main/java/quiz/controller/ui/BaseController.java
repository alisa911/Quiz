package quiz.controller.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BaseController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
