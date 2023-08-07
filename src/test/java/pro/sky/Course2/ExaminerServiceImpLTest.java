package pro.sky.Course2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Course2.Services.ExaminerServiceImpl;
import pro.sky.Course2.Services.JavaQuestionService;
import pro.sky.Course2.Services.MathQuestionService;

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
    Question question3 = new Question("3+3", "6");
    Question question4 = new Question("6", "3+3");

    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;
    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void getQuestionsTest(){
        javaQuestionService.add(question);
        mathQuestionService.add(question3);
        ArrayList<Question> test=new ArrayList<>();
        test.add(question);
        test.add(question3);
        when(javaQuestionService.getRandomQuestion()).thenReturn(question);
        when(javaQuestionService.getSize()).thenReturn(1);
        when(mathQuestionService.getRandomQuestion()).thenReturn(question3);
        when(mathQuestionService.getSize()).thenReturn(1);
        assertIterableEquals(test, out.getQuestions(2));
    }
}
