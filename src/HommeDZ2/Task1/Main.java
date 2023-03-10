package HommeDZ2.Task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList <Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        UniqueElement(list);

    }
    public static <T> void UniqueElement(ArrayList<T> list){
      List <T> unique = new ArrayList<>(list);
      for (int i = 0; i < unique.size(); i++){
          System.out.print( unique.get(i));
      }
    }
}
