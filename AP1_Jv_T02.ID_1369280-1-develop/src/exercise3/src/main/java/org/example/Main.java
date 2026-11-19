import java.util.*;

abstract class Animal {
    private String name;
    private int age;

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}

interface Herbivore {
    String chill();
}

interface Omnivore {
    String hunt();
}

class Dog extends Animal implements Omnivore {
    public Dog(String name, int age) {
        super(name, age);
    }

    public String hunt() {
        return "I can hunt for robbers";
    }

    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge() + ". " + hunt();
    }
}

class Cat extends Animal implements Omnivore {
    public Cat(String name, int age) {
        super(name, age);
    }

    public String hunt() {
        return "I can hunt for mice";
    }

    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge() + ". " + hunt();
    }
}

class Hamster extends Animal implements Herbivore {
    public Hamster(String name, int age) {
        super(name, age);
    }

    public String chill() {
        return "I can chill for 8 hours";
    }

    public String toString() {
        return "Hamster name = " + getName() + ", age = " + getAge() + ". " + chill();
    }
}

class GuineaPig extends Animal implements Herbivore {
    public GuineaPig(String name, int age) {
        super(name, age);
    }

    public String chill() {
        return "I can chill for 12 hours";
    }

    public String toString() {
        return "GuineaPig name = " + getName() + ", age = " + getAge() + ". " + chill();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();

        int n = readInt(sc);

        for (int i = 0; i < n; i++) {
            String type = sc.nextLine();
            String name;
            int age;

            switch (type) {
                case "dog":
                    name = sc.nextLine();
                    age = readInt(sc);
                    if (age <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                        break;
                    }
                    pets.add(new Dog(name, age));
                    break;

                case "cat":
                    name = sc.nextLine();
                    age = readInt(sc);
                    if (age <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                        break;
                    }
                    pets.add(new Cat(name, age));
                    break;

                case "hamster":
                    name = sc.nextLine();
                    age = readInt(sc);
                    if (age <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                        break;
                    }
                    pets.add(new Hamster(name, age));
                    break;

                case "guinea":
                    name = sc.nextLine();
                    age = readInt(sc);
                    if (age <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                        break;
                    }
                    pets.add(new GuineaPig(name, age));
                    break;

                default:
                    System.out.println("Incorrect input. Unsupported pet type");
            }
        }

        pets.stream()
                .filter(p -> p instanceof Herbivore)
                .forEach(System.out::println);

        pets.stream()
                .filter(p -> p instanceof Omnivore)
                .forEach(System.out::println);
    }

    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }
}
