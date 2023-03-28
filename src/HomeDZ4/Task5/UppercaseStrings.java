package HomeDZ4.Task5;

import java.util.List;
import java.util.stream.Collectors;

public class UppercaseStrings {
    public static void main(String[] args) {
 System.out.print (List.of("abc", "def", "qqq").stream().map(x -> x.toUpperCase()).collect(Collectors.joining(",")));
    }
}
