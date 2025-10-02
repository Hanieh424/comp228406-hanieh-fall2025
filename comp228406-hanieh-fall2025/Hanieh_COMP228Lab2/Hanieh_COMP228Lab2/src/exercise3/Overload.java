package exercise3;

public class Overload {

    public static int calculate(int a, int b) {
        return a + b;
    }


    public static int calculate(int a, int b, int c) {
        return a + b + c;
    }

    public static double calculate(double a, double b) {
        return a + b ;

    }public static void main(String[] args){
         int sumTwo = calculate(5, 10);
         int sumThree = calculate(3, 7, 11);
         double sumDouble = calculate(4.5, 6.7);


         System.out.println("Sum of two integers:" + sumTwo);
         System.out.println("Sum of three integers:" + sumThree);
         System.out.println("Sum of two Doubles:" + sumDouble);
    }
}
