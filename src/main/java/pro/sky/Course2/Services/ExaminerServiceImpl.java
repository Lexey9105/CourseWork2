package pro.sky.Course2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Question;
import pro.sky.Course2.Repository.JavaQuestionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;



@Service
public class ExaminerServiceImpl implements ExaminerService{

   // private final JavaQuestionService javaQuestionService;
    //private final MathQuestionService mathQuestionService;
   private final JavaQuestionRepository javaQuestionRepository;
    private final Random random;

    @Autowired
    public ExaminerServiceImpl(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository=javaQuestionRepository;
        this.random = new Random();
    }

    private int maxQuestionsNumber() {
        return javaQuestionRepository.getSize();
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
            if (randomGenerator() > 0 && javaQuestionRepository.getSize() > 0) {
                examQuestions.add(javaQuestionRepository.getRandomQuestionJava());
            } else if (javaQuestionRepository.getSize() > 0) {
                examQuestions.add(javaQuestionRepository.getRandomQuestionMath());
            }
        }

        return examQuestions;
    }

}