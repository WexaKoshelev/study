package HomeDZ.Task6;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        String s = "01.01.1900";
        DateFormat df = new SimpleDateFormat("MM.dd.yyyy");
        Date startDate;
        startDate = df.parse(s);

            System.out.println(startDate);
    }
}
