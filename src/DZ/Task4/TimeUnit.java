package DZ.Task4;

public class TimeUnit {
    private final int LOW_BOUND;
    private final int MIDDAY_BOUND;
    private final int HOUR_BOUND;
    private final int MIN_AND_SEC_BOUND;
    private int hours;
    private int minutes;
    private int seconds;

    public TimeUnit(int hours, int minutes, int seconds) {
        this.LOW_BOUND = 0;
        this.MIDDAY_BOUND = 12;
        this.HOUR_BOUND = 24;
        this.MIN_AND_SEC_BOUND = 60;
        if (this.timeValidate(hours, minutes, seconds)) {
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        }

    }

    public TimeUnit(int hours, int minutes) {
        this(hours, minutes, 0);
    }

    public TimeUnit(int hours) {
        this(hours, 0, 0);
    }

    public void time24HourPrint() {
        System.out.printf("%02d:%02d:%02d\n", this.hours, this.minutes, this.seconds);
    }

    public void time12HourPrint() {
        if (this.hours < 12) {
            System.out.printf("%02d:%02d:%02d\n", this.hours == 0 ? 12 : this.hours, this.minutes, this.seconds);
        } else {
            System.out.printf("%02d:%02d:%02d\n", this.hours == 12 ? this.hours : this.hours - 12, this.minutes, this.seconds);
        }

    }

    public void timeAdd(int hours, int minutes, int seconds) {
        if (this.timeValidate(hours, minutes, seconds)) {
            this.hours = (this.hours + hours + (this.minutes + minutes + (this.seconds + seconds) / 60) / 60) % 24;
            this.minutes = (this.minutes + minutes + (this.seconds + seconds) / 60) % 60;
            this.seconds = (this.seconds + seconds) % 60;
        }

    }

    private boolean timeValidate(int hours, int minutes, int seconds) {
        if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60 && seconds >= 0 && seconds < 60) {
            return true;
        } else {
            System.out.println("Данные времени не корректны");
            return false;
        }
    }
}
