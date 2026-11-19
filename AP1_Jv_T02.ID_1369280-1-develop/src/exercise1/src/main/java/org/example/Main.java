import java.util.*;

abstract class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}

class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge();
    }
}

class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();

        int n = scanner.nextInt();
        scanner.nextLine();
        String type;
        for (int i = 0; i < n; i++) {
            type = scanner.nextLine();
            if (checkType(type)) {
                continue;
            }

            String name = scanner.nextLine();

            int age = scanner.nextInt();
            scanner.nextLine();
            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
                continue;
            }

            switch (type) {
                case "dog":
                    pets.add(new Dog(name, age));
                    break;
                case "cat":
                    pets.add(new Cat(name, age));
                    break;
                default:
                    System.out.println("Incorrect input. Unsupported pet type");
            }
        }

        for (Animal pet : pets) {
            System.out.print(pet + "  ");
        }
        System.out.println();
        scanner.close();
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }
    public static boolean checkType(String type) {
        String dog = "dog";
        boolean flag = true;
        if (type.equals(dog) || type.equals("cat")) {
            flag = false;
        } else {
            System.out.println("Incorrect input. Unsupported pet type");
        }
        return flag;
    }
}
