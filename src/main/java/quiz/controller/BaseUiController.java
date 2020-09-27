package quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseUiController {

    @RequestMapping("/")
    public String home() {
        return "Hello! My frontend is still in development, but you can use Postman to view my data";
    }
}
