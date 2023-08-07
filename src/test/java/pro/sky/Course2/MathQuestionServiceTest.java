package pro.sky.Course2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Repository.JavaQuestionRepository;
import pro.sky.Course2.Services.MathQuestionService;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {


    @Mock
    private JavaQuestionRepository javaQuestionRepository;

    @InjectMocks
    private MathQuestionService out;


    String add1="1+1";
    String add2="2";

    Question question = new Question(add1, add2);
    public static Stream<Arguments> paramsForTest1() {
        return Stream.of(
                Arguments.of("1+1","2"),
                Arguments.of("2-1", "1"),
                Arguments.of("3*3", "9"),
                Arguments.of("4/4", "1")
        );
    }

    public static Stream<Arguments> paramsForTest2() {
        return Stream.of(
                Arguments.of("",""),
                Arguments.of(" ", " "),
                Arguments.of(null, null)
        );
    }



    @Test
    public void addTest() {
        Question question1 = new Question(add1, add2);
        when(javaQuestionRepository.add(question)).thenReturn(question);
        assertEquals(question, out.add(add1, add2));
    }


    @ParameterizedTest
    @MethodSource("paramsForTest2")
    public void addTestException(String query, String answer) {
        assertThrows(EmptyRequestException.class, () -> {
            out.add(query, answer);
        });
    }







    @ParameterizedTest
    @MethodSource("paramsForTest1")
    public void getAllTest(String query, String answer) {
        Question question = new Question(query, answer);
        ArrayList<Question> test=new ArrayList<>();
        test.add(question);
        when(javaQuestionRepository.getAll()).thenReturn(test);
        out.add(query, answer);

        assertIterableEquals(test, out.getAll());
    }




}
