package com.school21;

import static java.lang.Math.sqrt;

public class MethodExamples {

    /**
     * Пример
     * <p>
     * Вычисление факториала
     */
    public static double factorial(int n) {
        double result = 1.0;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Пример
     * <p>
     * Проверка числа на простоту -- результат true, если число простое
     */
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int m = 3; m <= (int) sqrt(n); m += 2) {
            if (n % m == 0) return false;
        }
        return true;
    }

    /**
     * Пример
     * <p>
     * Проверка числа на совершенность -- результат true, если число совершенное
     */
    public static boolean isPerfect(int n) {
        if (n < 2) return false;
        int sum = 1;
        for (int m = 2; m <= n / 2; m++) {
            if (n % m == 0) {
                sum += m;
                if (sum > n) break;
            }
        }
        return sum == n && n != 1;
    }

    /**
     * Пример
     * <p>
     * Найти число вхождений цифры m в число n
     */
    public static int digitCountInNumber(int n, int m) {
        if (n < 0) n = -n;
        if (m < 0) m = -m;
        if (n == 0 && m == 0) return 1;
        if (n < 10) {
            return n == m ? 1 : 0;
        }
        return digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m);
    }
}
