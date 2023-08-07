package pro.sky.Course2.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Course2.Question;
import pro.sky.Course2.Services.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

private final QuestionService questionService;
public JavaQuestionController(QuestionService questionService){
this.questionService=questionService;
}
    @GetMapping("/add")
public Question add(@RequestParam String query,@RequestParam String answer){
    return questionService.add(query,answer);
}
    @GetMapping("/remove")
    public Question remove(@RequestParam String query,@RequestParam String answer){
        Question question=new Question(query,answer);
    return questionService.remove(question);
   }
   @GetMapping
public Collection<Question> getAll(){
    return questionService.getAll();
}
}
