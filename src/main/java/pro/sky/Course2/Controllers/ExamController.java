package pro.sky.Course2.Controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.Course2.Question;
import pro.sky.Course2.Services.ExaminerService;
import pro.sky.Course2.Services.ExaminerServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/exam/")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get")
    public Collection<Question> getQuestions(@RequestParam int amount) {
        return examinerService.getQuestions(amount);
    }

}
