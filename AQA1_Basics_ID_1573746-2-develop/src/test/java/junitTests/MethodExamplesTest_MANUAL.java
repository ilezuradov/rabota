package junitTests;

import com.school21.MethodExamples;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса MethodExamples
 * manual-solution - тесты написаны самостоятельно
 */
public class MethodExamplesTest_MANUAL {

    @Test
    @DisplayName("Тест вычисления факториала - проверяет корректность вычисления факториала для разных чисел")
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
    @DisplayName("Тест проверки простых чисел - проверяет корректность определения простых чисел")
    public void testIsPrime() {
        assertTrue(MethodExamples.isPrime(2), "2 должно быть простым");
        assertTrue(MethodExamples.isPrime(3), "3 должно быть простым");
        assertTrue(MethodExamples.isPrime(5), "5 должно быть простым");
        assertTrue(MethodExamples.isPrime(7), "7 должно быть простым");
        assertTrue(MethodExamples.isPrime(11), "11 должно быть простым");
        assertTrue(MethodExamples.isPrime(13), "13 должно быть простым");
        assertTrue(MethodExamples.isPrime(17), "17 должно быть простым");
        assertTrue(MethodExamples.isPrime(19), "19 должно быть простым");

        assertFalse(MethodExamples.isPrime(1), "1 не должно быть простым");
        assertFalse(MethodExamples.isPrime(4), "4 не должно быть простым");
        assertFalse(MethodExamples.isPrime(6), "6 не должно быть простым");
        assertFalse(MethodExamples.isPrime(8), "8 не должно быть простым");
        assertFalse(MethodExamples.isPrime(9), "9 не должно быть простым");
        assertFalse(MethodExamples.isPrime(10), "10 не должно быть простым");
        assertFalse(MethodExamples.isPrime(15), "15 не должно быть простым");

        assertFalse(MethodExamples.isPrime(-1), "-1 не должно быть простым");
        assertFalse(MethodExamples.isPrime(-5), "-5 не должно быть простым");
        assertFalse(MethodExamples.isPrime(0), "0 не должно быть простым");

        assertAll("Проверка простых чисел",
                () -> assertTrue(MethodExamples.isPrime(23), "23 должно быть простым"),
                () -> assertTrue(MethodExamples.isPrime(29), "29 должно быть простым"),
                () -> assertTrue(MethodExamples.isPrime(31), "31 должно быть простым")
        );

        assertAll("Проверка составных чисел",
                () -> assertFalse(MethodExamples.isPrime(21), "21 не должно быть простым"),
                () -> assertFalse(MethodExamples.isPrime(25), "25 не должно быть простым"),
                () -> assertFalse(MethodExamples.isPrime(27), "27 не должно быть простым")
        );
    }

    @Test
    @DisplayName("Тест проверки совершенных чисел - проверяет корректность определения совершенных чисел")
    public void testIsPerfect() {
        assertTrue(MethodExamples.isPerfect(6), "6 должно быть совершенным числом");
        assertTrue(MethodExamples.isPerfect(28), "28 должно быть совершенным числом");
        assertTrue(MethodExamples.isPerfect(496), "496 должно быть совершенным числом");
        assertTrue(MethodExamples.isPerfect(8128), "8128 должно быть совершенным числом");

        assertFalse(MethodExamples.isPerfect(1), "1 не должно быть совершенным числом");
        assertFalse(MethodExamples.isPerfect(2), "2 не должно быть совершенным числом");
        assertFalse(MethodExamples.isPerfect(3), "3 не должно быть совершенным числом");
        assertFalse(MethodExamples.isPerfect(10), "10 не должно быть совершенным числом");
        assertFalse(MethodExamples.isPerfect(20), "20 не должно быть совершенным числом");

        assertFalse(MethodExamples.isPerfect(-1), "-1 не должно быть совершенным числом");
        assertFalse(MethodExamples.isPerfect(0), "0 не должно быть совершенным числом");

        assertAll("Проверка совершенных чисел",
                () -> assertTrue(MethodExamples.isPerfect(6), "6 должно быть совершенным"),
                () -> assertTrue(MethodExamples.isPerfect(28), "28 должно быть совершенным"),
                () -> assertTrue(MethodExamples.isPerfect(496), "496 должно быть совершенным")
        );
    }

    @Test
    @DisplayName("Тест подсчета вхождений цифры - проверяет корректность подсчета цифры в числе")
    public void testDigitCountInNumber() {
        assertEquals(1, MethodExamples.digitCountInNumber(123, 1), "Цифра 1 встречается 1 раз в числе 123");
        assertEquals(1, MethodExamples.digitCountInNumber(123, 2), "Цифра 2 встречается 1 раз в числе 123");
        assertEquals(1, MethodExamples.digitCountInNumber(123, 3), "Цифра 3 встречается 1 раз в числе 123");
        assertEquals(0, MethodExamples.digitCountInNumber(123, 4), "Цифра 4 не встречается в числе 123");

        assertEquals(2, MethodExamples.digitCountInNumber(112, 1), "Цифра 1 встречается 2 раза в числе 112");
        assertEquals(2, MethodExamples.digitCountInNumber(121, 1), "Цифра 1 встречается 2 раза в числе 121");
        assertEquals(3, MethodExamples.digitCountInNumber(111, 1), "Цифра 1 встречается 3 раза в числе 111");

        assertEquals(1, MethodExamples.digitCountInNumber(5, 5), "Цифра 5 встречается 1 раз в числе 5");
        assertEquals(0, MethodExamples.digitCountInNumber(5, 3), "Цифра 3 не встречается в числе 5");

        assertEquals(1, MethodExamples.digitCountInNumber(0, 0), "Цифра 0 встречается 1 раз в числе 0");
        assertEquals(0, MethodExamples.digitCountInNumber(0, 1), "Цифра 1 не встречается в числе 0");

        assertAll("Проверка подсчета цифр",
                () -> assertEquals(2, MethodExamples.digitCountInNumber(122, 2), "Цифра 2 встречается 2 раза в числе 122"),
                () -> assertEquals(3, MethodExamples.digitCountInNumber(222, 2), "Цифра 2 встречается 3 раза в числе 222"),
                () -> assertEquals(1, MethodExamples.digitCountInNumber(12345, 5), "Цифра 5 встречается 1 раз в числе 12345")
        );
    }
}