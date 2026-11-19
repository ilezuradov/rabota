import java.util.*;

abstract class Animal {
    private String name;
    private int age;
    private double weight;

    public Animal(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }

    public abstract double getFeedInfoKg();
}

class Dog extends Animal {
    public Dog(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public double getFeedInfoKg() {
        return getWeight() * 0.3;
    }

    @Override
    public String toString() {
        return String.format(
                "Dog name = %s, age = %d, mass = %.2f, feed = %.2f",
                getName(), getAge(), getWeight(), getFeedInfoKg()
        );
    }
}

class Cat extends Animal {
    public Cat(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public double getFeedInfoKg() {
        return getWeight() * 0.1;
    }

    @Override
    public String toString() {
        return String.format(
                "Cat name = %s, age = %d, mass = %.2f, feed = %.2f",
                getName(), getAge(), getWeight(), getFeedInfoKg()
        );
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();

        int n = readInt(scanner);

        for (int i = 0; i < n; i++) {
            String type = scanner.nextLine();

            if (!type.equals("dog") && !type.equals("cat")) {
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }

            String name = scanner.nextLine();
            int age = readInt(scanner);

            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
                continue;
            }

            double weight = readDouble(scanner);

            if (weight <= 0) {
                System.out.println("Incorrect input. Mass <= 0");
                continue;
            }

            if (type.equals("dog")) {
                pets.add(new Dog(name, age, weight));
            } else {
                pets.add(new Cat(name, age, weight));
            }
        }

        for (Animal pet : pets) {
            System.out.println(pet);
        }
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }
}
