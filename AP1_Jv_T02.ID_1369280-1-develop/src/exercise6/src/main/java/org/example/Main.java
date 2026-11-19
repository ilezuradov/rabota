import java.util.*;

/* =======================
   Abstract class Animal
   ======================= */
abstract class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

/* =======================
   Dog class
   ======================= */
class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge();
    }
}

/* =======================
   Cat class
   ======================= */
class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge();
    }
}

/* =======================
   BaseIterator interface
   ======================= */
interface BaseIterator<T> {
    T next();
    boolean hasNext();
    void reset();
}

/* =======================
   AnimalIterator
   ======================= */
class AnimalIterator implements BaseIterator<Animal> {
    private List<Animal> animals;
    private int index = 0;

    public AnimalIterator(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public Animal next() {
        return animals.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < animals.size();
    }

    @Override
    public void reset() {
        index = 0;
    }
}

/* =======================
   Main class
   ======================= */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();

        int count = 0;
        while (true) {
            try {
                count = Integer.parseInt(scanner.next());
                break;
            } catch (Exception e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }

        for (int i = 0; i < count; i++) {
            String type = scanner.next().toLowerCase();

            if (!type.equals("dog") && !type.equals("cat")) {
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }

            String name = scanner.next();

            int age;
            try {
                age = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                System.out.println("Could not parse a number. Please, try again");
                i--;
                continue;
            }

            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
                continue;
            }

            if (type.equals("dog")) {
                pets.add(new Dog(name, age));
            } else {
                pets.add(new Cat(name, age));
            }
        }

        AnimalIterator iterator = new AnimalIterator(pets);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
