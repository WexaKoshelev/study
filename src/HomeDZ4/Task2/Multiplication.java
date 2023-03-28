package HomeDZ4.Task2;

import java.util.stream.IntStream;

public class Multiplication {
    public static void main(String[] args) {

       System.out.println( IntStream.of(1,2,3,4,5)
                .reduce((left, right) -> left * right)
                       .orElse(0));
    }
}
