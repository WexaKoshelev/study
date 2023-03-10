package HommeDZ2.Task3;

import java.util.Set;

public class PowerfulSet <T>{
    public <T> void intersection(Set<T> set1, Set<T> set2) {
        set1.retainAll(set2);
        System.out.print(set1);
    }
   	public <T> void union (Set<T> set1, Set<T> set2) {
        set1.addAll(set2);
        System.out.print(set1);
    }
    public <T> void relativeComplemen (Set<T> set1, Set<T> set2) {
        set1.removeAll(set2);
        System.out.print(set1);
    }
}

