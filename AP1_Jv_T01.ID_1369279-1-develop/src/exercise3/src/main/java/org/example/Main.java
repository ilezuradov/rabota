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

    public static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = readIntSafe(scanner);

        if (n < 0) {
            System.out.println("Invalid input");
        } else if (n > 40) {
            System.out.println("Too large n");
        } else {
            long result = fibonacciRecursive(n);
            System.out.println(result);
        }

        scanner.close();
    }
}