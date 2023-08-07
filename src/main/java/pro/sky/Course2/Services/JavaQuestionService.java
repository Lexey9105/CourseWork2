package pro.sky.Course2.Services;



import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.Course2.Exceptions.AlreadyAddException;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {

    private final ArrayList<Question> questions;
    private  int size=0;

    public JavaQuestionService() {
        this.questions = new ArrayList<>();
    }

    @Override
    public Question add(String query, String answer) {
        if (StringUtils.isBlank(query) == true || StringUtils.isBlank(answer) == true) {
            throw new EmptyRequestException();
        }
        Question question = new Question(query, answer);
        if (questions.contains(question)) {
            throw new AlreadyAddException();
        }
        questions.add(question);
        size++;
        return question;
    }

    @Override
    public Question add(Question question) {
        if (StringUtils.isBlank(question.getQuery()) == true || StringUtils.isBlank(question.getAnswer()) == true) {
            throw new EmptyRequestException();
        }
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

    @Override
    public Question getRandomQuestion() {
         Random random=new Random();
        int n= random.nextInt(questions.size());
        return questions.get(n);
    }

    public int getSize(){
        return size;
    }
}
