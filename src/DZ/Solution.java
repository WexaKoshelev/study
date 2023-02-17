package DZ;

import java.util.Scanner;

public class Solution {
    void game() {
        Scanner scanner = new Scanner(System.in);
        int number = (int)(Math.random() * 1001.0);

        while(true) {
            System.out.println("Угадайте число от 0 до 1000.");
            System.out.print("Введите число - ");
            int guessed = scanner.nextInt();
            if (guessed == number) {
                System.out.println("Вы угадали");
                return;
            }

            if (guessed < number) {
                System.out.println("Это число меньше загаданного.");
            } else {
                System.out.println("Это число больше загаданного.");
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.game();
    }
}
