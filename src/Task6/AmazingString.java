package Task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmazingString {

    private char[] amazing;

    AmazingString(char... amazing) {
        this.amazing = amazing;
    }

    AmazingString(String amazing) {
        this.amazing = amazing.toCharArray();
    }

    public void symbol(int i) {
        if (i > amazing.length) {
            System.out.println("Число " + i + " больше длинны строки " + amazing.length);
        }
        System.out.println(amazing[i - 1]);
    }

    public void length() {
        int n = 0;
        while (true) {
            try {
                int t = amazing[n++];
            } catch (ArrayIndexOutOfBoundsException ex) {
                break;
            }
        }
        System.out.println(n - 1);
    }

    public void screen() {
        for (int i = 0; i < amazing.length; i++) {
            System.out.print(amazing[i]);
        }
        System.out.println();
    }

    public void findsChar(char... element) {
        for (int i = 0; i < amazing.length; i++) {
            for (int j = 0; j < element.length; j++) {
            }
        }
    }
        public void findsString (String element) {}
    public void delet () {
      if ( amazing [0] == ' ') {
     char [] chars = Arrays.copyOfRange(amazing, 1, amazing.length);
     System.out.println(chars);
      }else {
          System.out.println(amazing);
      }
    }
    public void revers () {
        for (int i = amazing.length - 1; i >= 0; i--){
            System.out.print(amazing[i]);
        }
        System.out.println();
    }
}