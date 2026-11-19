import java.util.Scanner;

public class Main {

    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static boolean isTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        double area = Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0;
        return area > 0.000001;
    }

    public static double readDoubleSafe(Scanner scanner) {
        while (true) {
            if (!scanner.hasNext()) {
                scanner.next(); // очистка
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Чтение всех 6 чисел подряд (как в примере ТЗ)
        double x1 = readDoubleSafe(scanner);
        double y1 = readDoubleSafe(scanner);
        double x2 = readDoubleSafe(scanner);
        double y2 = readDoubleSafe(scanner);
        double x3 = readDoubleSafe(scanner);
        double y3 = readDoubleSafe(scanner);

        if (!isTriangle(x1, y1, x2, y2, x3, y3)) {
            System.out.println("It's not a triangle");
        } else {
            double side1 = calculateDistance(x1, y1, x2, y2);
            double side2 = calculateDistance(x2, y2, x3, y3);
            double side3 = calculateDistance(x3, y3, x1, y1);

            double perimeter = side1 + side2 + side3;
            System.out.printf("Perimeter: %.3f%n", perimeter);
        }

        scanner.close();
    }
}