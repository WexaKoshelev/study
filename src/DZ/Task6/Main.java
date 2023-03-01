package DZ.Task6;

public class Main {
    public static void main(String[] args) {
        AmazingString amazingString = new AmazingString(" String");
        AmazingString amazingString1 = new AmazingString('S', 't', 'r', 'i', 'n', 'g');
        amazingString.symbol(5);
        amazingString.screen();
        amazingString1.length();
        amazingString.revers();
        amazingString.delet();
    }
}
