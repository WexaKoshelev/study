package HomeDZ4.Task1;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sum {
    public static void main(String[] args) {
     System.out.println( IntStream.rangeClosed(1,100)
              .filter(x -> x % 2 == 0)
              .sum());

    }
}
