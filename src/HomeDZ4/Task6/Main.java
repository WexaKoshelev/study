package HomeDZ4.Task6;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Set<Set<Integer>> WinningLines = Set.of(Set.of(0, 1, 2), Set.of(3, 4, 5), Set.of(6, 7, 8), Set.of(9, 10, 11),
                Set.of(12, 13, 14), Set.of(15, 16, 17), Set.of(18, 19, 20), Set.of(21, 22, 23));
         WinningLines.stream().flatMap(Collection ::stream).collect(Collectors.toSet());
    }
}