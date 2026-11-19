import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 0;          // количество введённых чисел
        Integer previous = null; // предыдущее число
        int breakingNumber = 0;  // число, нарушившее порядок
        boolean isOrdered = true; // флаг упорядоченности

        // Читаем числа пока есть ввод
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int current = scanner.nextInt();
                count++;

                // Проверяем на возрастание
                if (previous != null && current <= previous) {
                    isOrdered = false;
                    breakingNumber = current;
                    break; // немедленно прекращаем при нарушении
                }

                previous = current;
            } else {
                // Встретили не число - завершаем ввод
                scanner.next(); // считываем нечисловое значение
                break;
            }
        }

        // Вывод результата (точно как в ТЗ)
        if (count == 0) {
            System.out.println("Input error");
        } else if (isOrdered) {
            System.out.println("The sequence is ordered in ascending order");
        } else {
            System.out.println("The sequence is not ordered from the ordinal number of the number " + breakingNumber);
        }

        scanner.close();
    }
}