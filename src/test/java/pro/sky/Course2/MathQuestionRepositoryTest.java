package pro.sky.Course2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.Course2.Exceptions.AlreadyAddException;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Repository.JavaQuestionRepository;
import pro.sky.Course2.Repository.MathQuestionRepository;
import pro.sky.Course2.Repository.QuestionRepository;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MathQuestionRepositoryTest {
    private final QuestionRepository questionRepository=new MathQuestionRepository();
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

    public static Stream<Arguments> paramsForTest3() {
        return Stream.of(
                Arguments.of("1+1","2"),
                Arguments.of("2-1", "1")
        );
    }



    @ParameterizedTest
    @MethodSource("paramsForTest1")
    public void addTest(String query, String answer) {
        Question question = new Question(query, answer);
        assertEquals(question, questionRepository.add(question));
    }




    @ParameterizedTest
    @MethodSource("paramsForTest3")
    public void addTestException2(String query, String answer) {
        Question question = new Question(query, answer);
        questionRepository.add(question);
        assertThrows(AlreadyAddException.class, () -> {
            questionRepository.add(question);
        });
    }

    @ParameterizedTest
    @MethodSource("paramsForTest1")
    public void removeTest(String query, String answer) {
        Question question = new Question(query, answer);
        questionRepository.add(question);
        assertEquals(question, questionRepository.remove(question));
    }

    @Test
    public void removeTestException() {
        Question question1 = new Question(add2, add1);
        questionRepository.add(question);
        assertThrows(EmptyRequestException.class, () -> {
            questionRepository.remove(question1);
        });
    }

    @ParameterizedTest
    @MethodSource("paramsForTest1")
    public void getAllTest(String query, String answer) {
        Question question1 = new Question(query, answer);
        questionRepository.add(question1);
        ArrayList<Question> test=new ArrayList<>();
        test.add(question1);
        assertIterableEquals(test, questionRepository.getAll());
    }




}


