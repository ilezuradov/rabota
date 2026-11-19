import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Метод для фильтрации строк (БЕЗ Stream API)
    public static List<String> filterStrings(List<String> strings, String substring) {
        List<String> result = new ArrayList<>();

        // Реализуем фильтрацию вручную
        for (String str : strings) {
            // Проверяем содержит ли строка подстроку
            if (containsSubstring(str, substring)) {
                result.add(str);
            }
        }

        return result;
    }

    // Метод проверки наличия подстроки (можно использовать String.contains())
    private static boolean containsSubstring(String str, String substring) {
        return str.contains(substring);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Читаем количество строк
        int count = Integer.parseInt(scanner.nextLine());

        // 2. Читаем строки в список
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            strings.add(scanner.nextLine());
        }

        // 3. Читаем подстроку для фильтрации
        String substring = scanner.nextLine();

        // 4. Фильтруем строки
        List<String> filteredStrings = filterStrings(strings, substring);

        // 5. Выводим результат
        if (filteredStrings.isEmpty()) {
            System.out.println(); // пустая строка как в примере
        } else {
            for (int i = 0; i < filteredStrings.size(); i++) {
                System.out.print(filteredStrings.get(i));
                if (i < filteredStrings.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}