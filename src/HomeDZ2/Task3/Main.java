package HomeDZ2.Task3;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set <Integer> set = new HashSet<>();
        set.add(0);
        set.add(1);
        set.add(2);
        set.add(4);
        Set <Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        PowerfulSet <Integer> powerfulSet = new PowerfulSet<>();
        powerfulSet.union(set,set1);
    }
}
