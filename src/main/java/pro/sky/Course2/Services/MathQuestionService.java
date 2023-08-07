package pro.sky.Course2.Services;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Question;
import pro.sky.Course2.Repository.JavaQuestionRepository;
import pro.sky.Course2.Repository.MathQuestionRepository;

import java.util.Collection;

@Service
@Qualifier("MathQuestionService")
public class MathQuestionService implements QuestionService {
    private final MathQuestionRepository mathQuestionRepository;


    public MathQuestionService(MathQuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    @Override
    public Question add(String query, String answer) {
        if (StringUtils.isBlank(query) == true || StringUtils.isBlank(answer) == true) {
            throw new EmptyRequestException();
        }
        Question question = new Question(query, answer);

        mathQuestionRepository.add(question);
        return question;
    }

    @Override
    public Question add(Question question) {
        if (StringUtils.isBlank(question.getQuery()) == true || StringUtils.isBlank(question.getAnswer()) == true) {
            throw new EmptyRequestException();
        }
        mathQuestionRepository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        mathQuestionRepository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }

    public int getSize() {
        return mathQuestionRepository.getSize();
    }

    @Override
    public Question getRandomQuestion() {

        return mathQuestionRepository.getRandomQuestion();
    }


}
