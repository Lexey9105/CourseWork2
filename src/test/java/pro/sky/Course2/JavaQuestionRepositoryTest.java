package pro.sky.Course2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Course2.Exceptions.AlreadyAddException;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Repository.JavaQuestionRepository;
import pro.sky.Course2.Repository.QuestionRepository;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class JavaQuestionRepositoryTest {

private final QuestionRepository questionRepository=new JavaQuestionRepository();
    String add1="query";
    String add2="answer";
Question question = new Question(add1, add2);
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
        assertThrows(AlreadyAddException.class, () -> {
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

