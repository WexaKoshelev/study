package HomeDZ2.Task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0, 1, 1, 2, 3, 4, 4, 5);
        List<Integer> list2 = List.of(0, 1, 1, 2, 3, 4, 4, 5);
        System.out.println(convert(list));
    }


    public static <T> Set<T> convert(List<T> sourceList) {
        return new HashSet<>(sourceList);
    }
}

