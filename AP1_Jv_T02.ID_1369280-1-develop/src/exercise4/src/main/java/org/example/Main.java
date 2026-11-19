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

class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge();
    }
}

class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();

        int n = readInt(sc);

        for (int i = 0; i < n; i++) {
            String type = sc.nextLine();

            if (!type.equals("dog") && !type.equals("cat")) {
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }

            String name = sc.nextLine();
            int age = readInt(sc);

            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
                continue;
            }

            pets.add(type.equals("dog")
                    ? new Dog(name, age)
                    : new Cat(name, age));
        }

        pets.stream()
                .map(p -> p.getAge() > 10
                        ? (p instanceof Dog
                        ? new Dog(p.getName(), p.getAge() + 1)
                        : new Cat(p.getName(), p.getAge() + 1))
                        : p)
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