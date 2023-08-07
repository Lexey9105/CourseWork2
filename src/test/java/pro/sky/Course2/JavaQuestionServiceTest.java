package pro.sky.Course2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Course2.Exceptions.AlreadyAddException;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Repository.JavaQuestionRepository;
import pro.sky.Course2.Services.ExaminerServiceImpl;
import pro.sky.Course2.Services.JavaQuestionService;
import pro.sky.Course2.Services.QuestionService;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    String add1="query";
    String add2="answer";
    Question question = new Question(add1, add2);
    Question question2 = new Question(add2, add1);
    @Mock
    private JavaQuestionRepository javaQuestionRepository;

    @InjectMocks
    private JavaQuestionService out;



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
        ArrayList<Question>test=new ArrayList<>();
        test.add(question);
        when(javaQuestionRepository.getAll()).thenReturn(test);
        out.add(query, answer);

        assertIterableEquals(test, out.getAll());
    }




}
