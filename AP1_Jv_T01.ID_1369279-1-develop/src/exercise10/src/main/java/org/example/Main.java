import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();

        // Read all input
        String inputLine = "";
        if (scanner.hasNextLine()) {
            inputLine = scanner.nextLine().trim();
        }

        Scanner inputScanner = new Scanner(inputLine);

        try {
            // Read number of users but ignore it - read ALL users in input
            if (!inputScanner.hasNextInt()) {
                return;
            }
            inputScanner.nextInt(); // Read and discard the number

            // Read ALL user data until end of input
            while (inputScanner.hasNext()) {
                if (!inputScanner.hasNext()) {
                    break;
                }
                String name = inputScanner.next();

                if (!inputScanner.hasNext()) {
                    break;
                }

                String ageInput = inputScanner.next();
                try {
                    int age = Integer.parseInt(ageInput);

                    if (age <= 0) {
                        errorMessages.add("Incorrect input. Age <= 0");
                        continue;
                    }

                    users.add(new User(name, age));
                } catch (NumberFormatException e) {
                    errorMessages.add("Could not parse a number. Please try again");
                    continue;
                }
            }
        } catch (Exception e) {
            // Handle any errors
        } finally {
            inputScanner.close();
        }

        // Print error messages if any
        for (String error : errorMessages) {
            System.out.print(error + "  ");
        }

        // Find adult users using Stream API
        List<String> adultUserNames = users.stream()
                .filter(User::isAdult)
                .map(User::getName)
                .toList();

        // Print adult user names
        if (!adultUserNames.isEmpty()) {
            System.out.print(String.join(", ", adultUserNames));
        }

        // Add newline at the end if we printed something
        if (!errorMessages.isEmpty() || !adultUserNames.isEmpty()) {
            System.out.println();
        }

        scanner.close();
    }
}