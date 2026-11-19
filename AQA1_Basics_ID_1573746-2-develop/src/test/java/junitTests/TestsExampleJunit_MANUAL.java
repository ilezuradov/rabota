package junitTests;

import com.school21.CustomException_MANUAL;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для демонстрации порядка выполнения
 * manual-solution - ручная реализация
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestsExampleJunit_MANUAL {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Сейчас автотесты будут запущены в верном порядке");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Спасибо за внимание!");
    }

    @Test
    @Order(1)
    @DisplayName("Первый тест - is1shouldBeThird (должен выполняться первым)")
    public void is1shouldBeThird() {
        System.out.println("Hi I am third test");
        System.out.println("Here is my assertion");
        assertTrue(145 > 0, "145 должно быть больше 0");
        System.out.println("Now I will perish");
        System.out.println("Bye");
    }

    @Test
    @Order(2)
    @DisplayName("Второй тест - is2shouldBeFirst (должен выполняться вторым)")
    public void is2shouldBeFirst() {
        System.out.println("Hi I am first test");
        System.out.println("Here is my assertion");
        assertTrue(6 > 5, "6 должно быть больше 5");
        System.out.println("Now I will perish");
        System.out.println("Bye");
    }

    @Test
    @Order(3)
    @DisplayName("Третий тест - is3shouldBeSecond (должен выполняться третьим)")
    public void is3shouldBeSecond() {
        System.out.println("Hi I am second test");
        System.out.println("Here is my assertion");
        assertEquals(10, 5 + 5, "5 + 5 должно равняться 10");
        System.out.println("Now I will perish");
        System.out.println("Bye");
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 200, 300, 400, 500})
    @Order(4)
    @DisplayName("Четвертый тест - параметризованный с выбросом CustomException")
    public void testWithCustomException(int value) {
        try {
            assertTrue(value < 150, "Значение должно быть меньше 150");
            System.out.println("Test passed with value: " + value);
        } catch (AssertionError e) {
            System.out.println("Test failed with value: " + value + ", throwing CustomException");
            throw new CustomException_MANUAL(
                    "Ошибка валидации данных: значение " + value + " не соответствует ожидаемому (должно быть < 150)",
                    e
            );
        }
    }
}