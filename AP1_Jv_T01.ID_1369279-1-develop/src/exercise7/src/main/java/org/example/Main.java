import java.io.*;
import java.util.Scanner;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Устанавливаем английскую локаль для чтения точек
        scanner.useLocale(Locale.US);

        // Читаем имя файла
        String fileName = scanner.nextLine().trim();

        try {
            // Создаем путь к файлу
            String projectDir = System.getProperty("user.dir");
            File file = new File(projectDir, fileName);

            if (!file.exists()) {
                System.out.println("Input error. File doesn't exist");
                return;
            }

            Scanner fileScanner = new Scanner(file);
            fileScanner.useLocale(Locale.US);

            // Читаем размер
            if (!fileScanner.hasNextInt()) {
                System.out.println("Input error. Invalid file format");
                fileScanner.close();
                return;
            }

            int size = fileScanner.nextInt();

            if (size <= 0) {
                System.out.println("Input error. Size <= 0");
                fileScanner.close();
                return;
            }

            // Читаем числа
            double[] numbers = new double[size];
            int count = 0;

            while (count < size && fileScanner.hasNext()) {
                if (fileScanner.hasNextDouble()) {
                    numbers[count] = fileScanner.nextDouble();
                    count++;
                } else {
                    // Пропускаем некорректный ввод
                    fileScanner.next();
                }
            }

            fileScanner.close();

            if (count < size) {
                System.out.println("Input error. Insufficient number of elements");
                return;
            }

            // Выводим результат (ТОЧНО КАК В ТЗ)
            System.out.print(count + " ");
            for (int i = 0; i < count; i++) {
                System.out.print(numbers[i]);
                if (i < count - 1) {
                    System.out.print(" ");
                }
            }

            // Находим min/max
            double min = numbers[0];
            double max = numbers[0];

            for (int i = 1; i < count; i++) {
                if (numbers[i] < min) min = numbers[i];
                if (numbers[i] > max) max = numbers[i];
            }

            System.out.println(" Saving min and max values in file");

            // Сохраняем в result.txt
            File resultFile = new File(projectDir, "result.txt");
            try (PrintWriter writer = new PrintWriter(resultFile)) {
                writer.println(min + " " + max);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Input error. File doesn't exist");
        }

        scanner.close();
    }
}