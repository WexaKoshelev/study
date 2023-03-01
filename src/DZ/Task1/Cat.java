package Task1;

public class Cat {
    private String sleep() {
        return "Sleep";
    }

    private String meow() {
        return "Meow";
    }

    private String eat() {
        return "Eat";
    }

    public String status() {
        int x = (int)(Math.random() * 3.0) + 1;
        if (x == 1) {
            return this.sleep();
        } else {
            return x == 2 ? this.meow() : this.eat();
        }
    }
}
