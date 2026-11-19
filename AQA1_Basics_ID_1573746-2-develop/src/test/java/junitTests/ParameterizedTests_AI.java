package junitTests;

import com.school21.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Параметризованные тесты
 * ai-assisted-solution - улучшено с помощью искусственного интеллекта
 */
public class ParameterizedTests_AI {

    @ParameterizedTest
    @ValueSource(strings = {
            "Alexander 25 years",
            "Ekaterina 30 years",
            "Vladimir 45 years",
            "Natalia 22 years",
            "Dmitry 35 years",
            "Konstantin 40 years",
            "Margarita 28 years"
    })
    @DisplayName("Тест валидации строк с данными о человеке: проверка корректных строк (happy path)")
    public void testValidatePersonStringHappyPath(String input) {
        assertTrue(Validator.validatePersonString(input.trim()),
                "Строка '" + input + "' должна быть валидной");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Bob 20 years",
            "Al 15 years",
            "Alexander 15 years",
            "Alex 17 years",
            "Alexander 25",
            "Invalid string",
            "",
            "John 20 year"
    })
    @DisplayName("Тест валидации строк с данными о человеке: проверка некорректных строк")
    public void testValidatePersonStringInvalid(String input) {
        assertFalse(Validator.validatePersonString(input.trim()),
                "Строка '" + input + "' должна быть невалидной");
    }

    @ParameterizedTest
    @MethodSource("palindromeProvider")
    @DisplayName("Тест проверки палиндромов: проверка различных строк с пробелами, спецсимволами и граничными случаями")
    public void testIsPalindrome(String input, boolean expected) {
        if (input == null) {
            assertEquals(expected, Validator.isPalindrome(null));
            return;
        }
        assertEquals(expected, Validator.isPalindrome(input.trim()),
                "Результат для строки '" + input + "' должен быть " + expected);
    }

    static Stream<Object[]> palindromeProvider() {
        return Stream.of(
                // Палиндромы
                new Object[]{"А роза упала на лапу Азора", true},
                new Object[]{"Madam", true},
                new Object[]{"madam", true},
                new Object[]{"12321", true},
                new Object[]{"", true},
                new Object[]{"   ", true},
                new Object[]{"A man a plan a canal Panama", true},
                new Object[]{"Racecar", true},
                new Object[]{"Never odd or even", true},
                new Object[]{"   a   ", true},
                // Не палиндромы
                new Object[]{"Hello", false},
                new Object[]{"Java", false},
                new Object[]{"!@#$%", false},
                new Object[]{"12345", false},
                new Object[]{"Testing", false},
                // Специальные случаи
                new Object[]{null, false},
                new Object[]{"A", true},
                new Object[]{"Aa", true}
        );
    }
}