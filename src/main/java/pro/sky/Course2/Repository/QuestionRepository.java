package pro.sky.Course2.Repository;

import pro.sky.Course2.Question;

import java.util.Collection;

public interface QuestionRepository {
        Question add(Question question);
        Question remove(Question question);
        Collection<Question> getAll();
    }

