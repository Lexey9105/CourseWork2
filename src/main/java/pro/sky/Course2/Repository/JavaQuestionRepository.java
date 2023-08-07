package pro.sky.Course2.Repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pro.sky.Course2.Exceptions.AlreadyAddException;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Repository

public class JavaQuestionRepository implements QuestionRepository{

    private final ArrayList<Question> questions;
    private  int size=0;

    public JavaQuestionRepository() {
        this.questions = new ArrayList<>();
    }
    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new AlreadyAddException();
        }
        questions.add(question);
        size++;
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)){
            throw new EmptyRequestException();
        }
        questions.remove(question);
        size--;
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }
    public int getSize(){
        return size;
    }

    public Question getRandomQuestion() {
        Random random=new Random();
        int n= random.nextInt(questions.size());
        return questions.get(n);
    }
}
