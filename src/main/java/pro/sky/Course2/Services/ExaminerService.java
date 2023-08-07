package pro.sky.Course2.Services;

import pro.sky.Course2.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}
