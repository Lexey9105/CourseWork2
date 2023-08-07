package pro.sky.Course2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService{

    private final JavaQuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;
    private final Random random;

    @Autowired
    public ExaminerServiceImpl(final JavaQuestionService javaQuestionService,
                               final MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
        this.random = new Random();
    }

    private int maxQuestionsNumber() {
        return mathQuestionService.getSize() + javaQuestionService.getSize();
    }

    private int randomGenerator() {
        return random.nextInt(2);
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > maxQuestionsNumber() || amount <= 0) {
            throw new EmptyRequestException();
        }
        ArrayList<Question> examQuestions=new ArrayList<>();
        while (examQuestions.size() < amount) {
            if (randomGenerator() > 0 && javaQuestionService.getSize() > 0) {
                examQuestions.add(javaQuestionService.getRandomQuestion());
            } else if (mathQuestionService.getSize() > 0) {
                examQuestions.add(mathQuestionService.getRandomQuestion());
            }
        }

        return examQuestions;
    }

}