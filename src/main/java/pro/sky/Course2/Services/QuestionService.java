package pro.sky.Course2.Services;

import pro.sky.Course2.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String query,String answer);
    Question add(Question question);
    Question remove(Question question);
    Collection <Question> getAll();
    Question getRandomQuestion();
    int getSize();



}
