import java.util.Scanner;

public class Main {

    public static double readDoubleSafe(Scanner scanner) {
        while (true) {
            if (!scanner.hasNext()) {
                scanner.next();
                continue;
            }

            String input = scanner.next().trim();
            input = input.replace(',', '.');

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please try again");
            }
        }
    }

    public static void selectionSort(double[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            double temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read size
        int size = 0;
        while (size <= 0) {
            String input = scanner.next();
            try {
                size = Integer.parseInt(input);
                if (size <= 0) {
                    System.out.println("Input error. Size <= 0");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please try again");
            }
        }

        // Read numbers
        double[] numbers = new double[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = readDoubleSafe(scanner);
        }

        // Sort
        selectionSort(numbers);

        // Output
        for (int i = 0; i < size; i++) {
            System.out.print(numbers[i]);
            if (i < size - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        scanner.close();
    }
}