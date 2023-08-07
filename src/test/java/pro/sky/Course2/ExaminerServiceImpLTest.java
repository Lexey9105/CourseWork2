package pro.sky.Course2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Course2.Services.ExaminerServiceImpl;
import pro.sky.Course2.Services.JavaQuestionService;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImpLTest {


    String add1="query";
    String add2="answer";
    Question question = new Question(add1, add2);
    Question question2 = new Question(add2, add1);
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void getQuestionsTest(){
        javaQuestionService.add(question);
        javaQuestionService.add(question2);
        Question question1 = new Question("query", "answer");
        ArrayList<Question> test=new ArrayList<>();
        test.add(question1);
        when(javaQuestionService.getRandomQuestion()).thenReturn(question);
        when(javaQuestionService.getSize()).thenReturn(1);
        assertIterableEquals(test, out.getQuestions(1));
    }
}
