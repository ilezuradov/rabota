import java.util.*;
import java.util.concurrent.*;
import java.util.Locale;

abstract class Animal {
    private String name;
    private int age;

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    public abstract double goToWalk();
}

class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public double goToWalk() {
        double time = getAge() * 0.5;
        try {
            TimeUnit.SECONDS.sleep((long) time);
        } catch (InterruptedException ignored) {}
        return time;
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
    public double goToWalk() {
        double time = getAge() * 0.25;
        try {
            TimeUnit.SECONDS.sleep((long) time);
        } catch (InterruptedException ignored) {}
        return time;
    }

    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge();
    }
}

class WalkResult {
    String text;
    double endTime;

    WalkResult(String text, double endTime) {
        this.text = text;
        this.endTime = endTime;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
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

            if (type.equals("dog")) {
                pets.add(new Dog(name, age));
            } else {
                pets.add(new Cat(name, age));
            }
        }

        ExecutorService pool = Executors.newFixedThreadPool(pets.size());
        List<Future<WalkResult>> futures = new ArrayList<>();

        for (int i = 0; i < pets.size(); i++) {
            Animal pet = pets.get(i);
            double startTime = (i + 1) * 0.1;

            futures.add(pool.submit(() -> {
                double walkTime = pet.goToWalk();
                double endTime = startTime + walkTime;

                String text = pet +
                        ", start time = " +
                        String.format(Locale.US, "%.2f", startTime) +
                        ", end time = " +
                        String.format(Locale.US, "%.2f", endTime);

                return new WalkResult(text, endTime);
            }));
        }

        List<WalkResult> results = new ArrayList<>();
        for (Future<WalkResult> f : futures) {
            results.add(f.get());
        }

        results.sort(Comparator.comparingDouble(r -> r.endTime));

        for (WalkResult r : results) {
            System.out.println(r.text);
        }

        pool.shutdown();
    }

    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
    }
}
