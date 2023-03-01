package DZ.Task7;

public class TriangleChecker {
    public static boolean checker (double a, double c, double b){
        if ( a + c >= b && a + b >= c && c + b >= a){
            return true;
        }
        return false;
    }
}
