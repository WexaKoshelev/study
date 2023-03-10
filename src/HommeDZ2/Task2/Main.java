package HommeDZ2.Task2;

import java.io.Console;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String t = scanner.nextLine();
        System.out.print(StringCheck(s,t));
    }

    public static boolean StringCheck(String str1, String str2) {
        str1.toLowerCase();
        str2.toLowerCase();
        if (str1.length() != str2.length()) {
            return false;
        }
        int count [] = new int[256];
        for (int i = 0; i < str1.length(); i++){
            count [str1.charAt(i)]++;
            count [str2.charAt(i)]--;
        }
        for (int i = 0; i < 256; i++){
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
