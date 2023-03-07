package HomeDZ.Task4;

import HomeDZ.Task1.MyCheckedException;

public class MyEvenNumber {
   private int number;
   public MyEvenNumber(int number) throws MyCheckedException {
       if (number % 2 ==0) {
           this.number = number;
       } else {
         throw new MyCheckedException("Введено нечетное число");
       }
   }


    public static void main(String[] args) throws MyCheckedException {
        MyEvenNumber myEvenNumber = new MyEvenNumber(3);
    }
}

