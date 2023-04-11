package HomeDZ2.Task2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(isAnagram("бейсбол", "бобслей"));
    }


    public static boolean isAnagram(String str1, String str2) {
        char[] charArr1 = str1.replace(" ", "").toCharArray();
        char[] charArr2 = str2.replace(" ", "").toCharArray();

        if (charArr1.length != charArr2.length) {
            return false;
        }

        Arrays.sort(charArr1);
        Arrays.sort(charArr2);
        return Arrays.equals(charArr1, charArr2);
    }
}

