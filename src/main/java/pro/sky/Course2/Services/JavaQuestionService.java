package pro.sky.Course2.Services;


import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.Course2.Exceptions.AlreadyAddException;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Question;
import pro.sky.Course2.Repository.JavaQuestionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Service
@Qualifier("JavaQuestionService")
public class JavaQuestionService implements QuestionService {

    private final JavaQuestionRepository javaQuestionRepository;


    public JavaQuestionService(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public Question add(String query, String answer) {
        if (StringUtils.isBlank(query) == true || StringUtils.isBlank(answer) == true) {
            throw new EmptyRequestException();
        }
        Question question = new Question(query, answer);

        javaQuestionRepository.add(question);
        return question;
    }

    @Override
    public Question add(Question question) {
        if (StringUtils.isBlank(question.getQuery()) == true || StringUtils.isBlank(question.getAnswer()) == true) {
            throw new EmptyRequestException();
        }
        javaQuestionRepository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        javaQuestionRepository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }



    @Override
    public Question getRandomQuestion() {

        return javaQuestionRepository.getRandomQuestionJava();
    }
    public int getSize() {return 0;}

}
