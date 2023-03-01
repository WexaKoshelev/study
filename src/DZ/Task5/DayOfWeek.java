package DZ.Task5;

public class DayOfWeek {
    private String week;
    private byte number;
    public DayOfWeek (byte number, String week){
        this.week = week;
        this.number = number;
    }
    public void displyu () {
        System.out.println(number + " " + week);
    }
}
