public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Проверка, является ли пользователь взрослым (18+ лет)
    public boolean isAdult() {
        return age >= 18;
    }
}