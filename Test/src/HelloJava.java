import javax.swing.*;

public class HelloJava {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello, Java!");
        frame.add(new HelloComponent());
        frame.setSize(300,300);
        frame.setVisible(true);
    }
}