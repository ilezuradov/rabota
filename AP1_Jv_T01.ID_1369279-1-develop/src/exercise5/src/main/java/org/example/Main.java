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

    public static boolean hasMatchingFirstLastDigits(int number) {
        number = Math.abs(number);

        if (number < 10) {
            return true;
        }

        int lastDigit = number % 10;

        int firstDigit = number;
        while (firstDigit >= 10) {
            firstDigit /= 10;
        }

        return firstDigit == lastDigit;
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

        // Read and process numbers
        int[] matchingNumbers = new int[size];
        int matchingCount = 0;

        for (int i = 0; i < size; i++) {
            int num = readIntSafe(scanner);

            if (hasMatchingFirstLastDigits(num)) {
                matchingNumbers[matchingCount] = num;
                matchingCount++;
            }
        }

        // Output
        if (matchingCount == 0) {
            System.out.println("There are no such elements");
        } else {
            for (int i = 0; i < matchingCount; i++) {
                System.out.print(matchingNumbers[i]);
                if (i < matchingCount - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}