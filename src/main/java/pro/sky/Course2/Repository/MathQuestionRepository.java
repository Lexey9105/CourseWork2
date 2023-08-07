package pro.sky.Course2.Repository;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pro.sky.Course2.Exceptions.AlreadyAddException;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Question;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Repository

public class MathQuestionRepository implements QuestionRepository {
    private final ArrayList<Question> mathQuestions;
    private int size = 0;

    public MathQuestionRepository() {
        this.mathQuestions = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        mathQuestions.add(new Question(
                "643-345",
                "298"));
        mathQuestions.add(new Question(
                "643+345",
                "988"));
        mathQuestions.add(new Question(
                "643*345",
                "221 835"));
        mathQuestions.add(new Question(
                "643/345",
                "1.86"));
    }

    @Override
    public Question add(Question question) {
        if (mathQuestions.contains(question)) {
            throw new AlreadyAddException();
        }
        mathQuestions.add(question);
        size++;
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!mathQuestions.contains(question)) {
            throw new EmptyRequestException();
        }
        mathQuestions.remove(question);
        size--;
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestions;
    }

    public int getSize() {
        return mathQuestions.size();
    }

    public Question getRandomQuestion() {
        Random random = new Random();
        int n = random.nextInt(mathQuestions.size());
        return mathQuestions.get(n);
    }
}

