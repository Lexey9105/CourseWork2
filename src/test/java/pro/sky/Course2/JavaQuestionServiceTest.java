package pro.sky.Course2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.Course2.Exceptions.AlreadyAddException;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Services.JavaQuestionService;
import pro.sky.Course2.Services.QuestionService;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {

private final QuestionService questionService=new JavaQuestionService();

    public static Stream<Arguments> paramsForTest1() {
        return Stream.of(
                Arguments.of("Что означает константная сложность?"," Константная сложность -алгоритм всегда будет использовать одинаковое количество ресурсов"),
                Arguments.of("какие разновидности цикла вы знаете?", "while,do..while,for,for each.."),
                Arguments.of("где хранятся примитивные переменные?", "примитивные переменные хранятся в стеке."),
                Arguments.of("Чем java отличается от python?", "программы Python выполняются с помощью интерпретатора, а вот приложения на Java транслируются в байт-код и выполняются с помощью виртуальной машины."),
                Arguments.of("Назовите основные принципы ООП?", "Абстракция,Инкапсуляция,Наследование,Полиморфизм")
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
                Arguments.of("Что означает константная сложность?"," Константная сложность -алгоритм всегда будет использовать одинаковое количество ресурсов"),
                Arguments.of("какие разновидности цикла вы знаете?", "while,do..while,for,for each..")
        );
    }

    public static Stream<Arguments> paramsForTest4() {
        return Stream.of(
                Arguments.of("Чтостантная сложность?"," Константожность -алгоритм всегда будет использовать одинаковое количество ресурсов"),
                Arguments.of("какие рацикла вы знаете?", "whille,for,for each..")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsForTest1")
    public void addTest(String query, String answer) {
        Question question = new Question(query, answer);
        assertEquals(question, questionService.add(query, answer));
    }

    @ParameterizedTest
    @MethodSource("paramsForTest1")
    public void addTest2(String query, String answer) {
        Question question = new Question(query, answer);
        assertEquals(question, questionService.add(question));
    }

    @ParameterizedTest
    @MethodSource("paramsForTest2")
    public void addTestException(String query, String answer) {
        assertThrows(EmptyRequestException.class, () -> {
            questionService.add(query, answer);
        });
    }

    @ParameterizedTest
    @MethodSource("paramsForTest3")
    public void addTestException2(String query, String answer) {
        questionService.add(query, answer);
        assertThrows(AlreadyAddException.class, () -> {
            questionService.add(query, answer);
        });
    }

    @ParameterizedTest
    @MethodSource("paramsForTest1")
    public void removeTest(String query, String answer) {
        Question question = new Question(query, answer);
        questionService.add(query, answer);
        assertEquals(question, questionService.remove(question));
    }

    @ParameterizedTest
    @MethodSource("paramsForTest4")
    public void removeTestException(String query, String answer) {
        Question question = new Question(query, answer);
        questionService.add("query", "answer");
        assertThrows(EmptyRequestException.class, () -> {
            questionService.remove(question);
        });
    }

    @ParameterizedTest
    @MethodSource("paramsForTest1")
    public void getAllTest(String query, String answer) {
        Question question = new Question(query, answer);
        questionService.add(query, answer);
        ArrayList<Question>test=new ArrayList<>();
        test.add(question);
        assertIterableEquals(test, questionService.getAll());
    }




}
