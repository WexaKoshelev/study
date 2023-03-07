package HomeDZ.Task5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception  {

        int n = inputN();
        System.out.println("Успешный ввод!");

    }

    private static int inputN() throws Exception{
        System.out.println("Введите число n, 0 < n < 100");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n < 100 && n > 0) {
            throw new Exception("Неверный ввод");
        }
        return n;
    }

}
