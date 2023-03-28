package HomeDZ4.Task3;

import java.awt.*;
import java.util.List;
import java.util.stream.Stream;

public class ListOfStrings {
    public static void main(String[] args) {
        List <String> list = List.of("One", "", "", "Two","Three");
        Long count = list.stream().filter(lis -> lis.length() > 1).count();
        System.out.println(count);
    }
}
