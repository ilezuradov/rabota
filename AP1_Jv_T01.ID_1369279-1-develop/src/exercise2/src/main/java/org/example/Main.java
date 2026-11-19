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

    public static int[] calculateTime(int totalSeconds) {
        int[] result = new int[3];

        if (totalSeconds < 0) {
            return null;
        }

        result[0] = totalSeconds / 3600;
        result[1] = (totalSeconds % 3600) / 60;
        result[2] = totalSeconds % 60;

        return result;
    }

    public static void outputResult(int totalSeconds, int[] time) {
        if (time == null) {
            System.out.println("Incorrect time");
            return;
        }

        System.out.printf("%02d:%02d:%02d%n", time[0], time[1], time[2]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input
        int seconds = readIntSafe(scanner);

        // Calculation
        int[] time = calculateTime(seconds);

        // Output
        outputResult(seconds, time);

        scanner.close();
    }
}