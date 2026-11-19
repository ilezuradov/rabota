import java.util.Scanner;

public class Main {

    public static int readIntSafe(Scanner scanner) {
        while (true) {
            if (!scanner.hasNext()) {
                scanner.next();
                continue;
            }

            String input = scanner.next().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please try again");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read size
        int size = readIntSafe(scanner);

        if (size <= 0) {
            System.out.println("Input error. Size <= 0");
            scanner.close();
            return;
        }

        // Read numbers
        int negativeCount = 0;
        int negativeSum = 0;

        for (int i = 0; i < size; i++) {
            int num = readIntSafe(scanner);

            if (num < 0) {
                negativeCount++;
                negativeSum += num;
            }
        }

        // Output
        if (negativeCount == 0) {
            System.out.println("There are no negative elements");
        } else {
            double average = (double) negativeSum / negativeCount;
            // Для целочисленного вывода как в примере ТЗ
            if (average == (int) average) {
                System.out.println((int) average);
            } else {
                System.out.println(average);
            }
        }

        scanner.close();
    }
}