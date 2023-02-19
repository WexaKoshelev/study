package Task7;

public class TriangleChecker {
    public static boolean Checker (double a, double c, double b){
        if ( a + c >= b && a + b >= c && c + b >= a){
            return true;
        }
        return false;
    }
}
