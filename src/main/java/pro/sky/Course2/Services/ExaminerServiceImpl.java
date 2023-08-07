package pro.sky.Course2.Services;

import org.springframework.stereotype.Service;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Question;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ExaminerServiceImpl implements ExaminerService{
   private final ArrayList<Question> examQuestions;
    private final QuestionService questionService;
   public ExaminerServiceImpl(QuestionService questionService){
       this.questionService=questionService;
       this.examQuestions=new ArrayList<>();
   }



    @Override
    public Collection<Question> getQuestions(int amount) {
if (amount>questionService.getSize()){throw new EmptyRequestException();
}
else if (amount==0){return examQuestions;}
else {
    int addQuest = amount;
        Question question = questionService.getRandomQuestion();
        if (!examQuestions.contains(question)) {
            examQuestions.add(question);
            addQuest--;
            getQuestions(addQuest);
        }else  {
            getQuestions(addQuest);
        }
     return examQuestions;}}
}
