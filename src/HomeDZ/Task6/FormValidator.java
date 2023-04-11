package HomeDZ.Task6;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormValidator extends Exception {
    public static void checkName (String str) {
        String regEx = "[A-Z]\\A";
        if (str.length() < 2 && str.length() > 20 && str.matches(regEx)) {
            System.out.print("Длина имени должна быть от 2 до 20 символов, первая буква заглавная");
        }
    }

    public static void checkBirthdate (String str) throws ParseException {
        String s = "01.01.1900";
        DateFormat df1 = new SimpleDateFormat("MM.dd.yyyy");
        DateFormat df2 = new SimpleDateFormat("MM.dd.yyyy");
        Date strDate;
        Date startDate;
        strDate = df2.parse(str);
        startDate = df1.parse(s);
        Date date = new Date();
        if (startDate.getTime() > strDate.getTime() && date.getTime() < strDate.getTime()) {
            System.out.print("Дата рождения должна быть не раньше 01.01.1900 и не позже текущей даты.");
        }
    }
}
