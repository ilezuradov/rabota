package junitTests;
import com.school21.MethodExamples;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса MethodExamples
 * ai-assisted-solution - улучшено с помощью искусственного интеллекта
 */
public class MethodExamplesTest_AI {
    
    @Test
    @DisplayName("Тест вычисления факториала: проверка корректной работы метода factorial с разными числами")
    public void testFactorial() {
        assertEquals(1.0, MethodExamples.factorial(0), 0.001, "Факториал 0 должен быть 1");
        assertEquals(1.0, MethodExamples.factorial(1), 0.001, "Факториал 1 должен быть 1");
        assertEquals(2.0, MethodExamples.factorial(2), 0.001, "Факториал 2 должен быть 2");
        assertEquals(6.0, MethodExamples.factorial(3), 0.001, "Факториал 3 должен быть 6");
        assertEquals(24.0, MethodExamples.factorial(4), 0.001, "Факториал 4 должен быть 24");
        assertEquals(120.0, MethodExamples.factorial(5), 0.001, "Факториал 5 должен быть 120");
        
        assertAll("Проверка факториалов",
            () -> assertEquals(720.0, MethodExamples.factorial(6), 0.001, "6! = 720"),
            () -> assertEquals(5040.0, MethodExamples.factorial(7), 0.001, "7! = 5040"),
            () -> assertEquals(40320.0, MethodExamples.factorial(8), 0.001, "8! = 40320")
        );
    }
    
    @Test
    @DisplayName("Тест проверки простых чисел: проверка корректной работы метода isPrime")
    public void testIsPrime() {
        assertTrue(MethodExamples.isPrime(2), "2 должно быть простым");
        assertTrue(MethodExamples.isPrime(3), "3 должно быть простым");
        assertTrue(MethodExamples.isPrime(5), "5 должно быть простым");
        assertTrue(MethodExamples.isPrime(7), "7 должно быть простым");
        assertTrue(MethodExamples.isPrime(11), "11 должно быть простым");
        
        assertFalse(MethodExamples.isPrime(1), "1 не должно быть простым");
        assertFalse(MethodExamples.isPrime(4), "4 не должно быть простым");
        assertFalse(MethodExamples.isPrime(6), "6 не должно быть простым");
        assertFalse(MethodExamples.isPrime(8), "8 не должно быть простым");
        assertFalse(MethodExamples.isPrime(9), "9 не должно быть простым");
        
        assertAll("Проверка простых чисел",
            () -> assertTrue(MethodExamples.isPrime(23), "23 должно быть простым"),
            () -> assertTrue(MethodExamples.isPrime(29), "29 должно быть простым"),
            () -> assertTrue(MethodExamples.isPrime(31), "31 должно быть простым")
        );
    }
    
    @Test
    @DisplayName("Тест проверки совершенных чисел: проверка корректной работы метода isPerfect")
    public void testIsPerfect() {
        assertTrue(MethodExamples.isPerfect(6), "6 должно быть совершенным числом");
        assertTrue(MethodExamples.isPerfect(28), "28 должно быть совершенным числом");
        assertTrue(MethodExamples.isPerfect(496), "496 должно быть совершенным числом");
        
        assertFalse(MethodExamples.isPerfect(1), "1 не должно быть совершенным числом");
        assertFalse(MethodExamples.isPerfect(2), "2 не должно быть совершенным числом");
        assertFalse(MethodExamples.isPerfect(10), "10 не должно быть совершенным числом");
        
        assertAll("Проверка совершенных чисел",
            () -> assertTrue(MethodExamples.isPerfect(6), "6 должно быть совершенным"),
            () -> assertTrue(MethodExamples.isPerfect(28), "28 должно быть совершенным"),
            () -> assertTrue(MethodExamples.isPerfect(496), "496 должно быть совершенным")
        );
    }
    
    @Test
    @DisplayName("Тест подсчета вхождений цифры: проверка корректной работы метода digitCountInNumber с разными числами")
    public void testDigitCountInNumber() {
        assertEquals(1, MethodExamples.digitCountInNumber(123, 1), "Цифра 1 встречается 1 раз в числе 123");
        assertEquals(2, MethodExamples.digitCountInNumber(112, 1), "Цифра 1 встречается 2 раза в числе 112");
        assertEquals(3, MethodExamples.digitCountInNumber(111, 1), "Цифра 1 встречается 3 раза в числе 111");
        
        assertAll("Проверка подсчета цифр",
            () -> assertEquals(2, MethodExamples.digitCountInNumber(122, 2), "Цифра 2 встречается 2 раза в числе 122"),
            () -> assertEquals(3, MethodExamples.digitCountInNumber(222, 2), "Цифра 2 встречается 3 раза в числе 222")
        );
    }
}
