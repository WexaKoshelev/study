package DZ.Task8;

public class Atm {
    private double course;
   private static int count;
    Atm (double course){
       this.course = course;
       count++;
    }
    public double dollarЕransfer (double amounts) {
        return amounts / course;
    }
    public double rublesЕransfer (double amounts) {
        return amounts * course;
    }
    public  void counter () {
        System.out.print(count);
    }
}
