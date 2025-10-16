package exercise2;
import java.util.Scanner;
public class GameTesterDriver {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter game tester name: ");
        String name = input.nextLine();

        System.out.println("Is the tester full-time or part-time? (F/P):");
        String type = input.nextLine().trim();

        GameTester tester;

        if (type.equalsIgnoreCase("F")) {
            tester = new FullTimeGameTester(name);
        } else if (type.equalsIgnoreCase("P")){
            System.out.println("Enter number of hours worked: ");
            int hours = input.nextInt();
            tester = new PartTimeGameTester(name, hours);
        } else {
            System.out.println("Invalid input.");
            input.close();
            return;
        }
        System.out.println("*** Game Tester Information ***");
        tester.displayInfo();
        System.out.println("Slary: $" + tester.determineSalary());

        input.close();
    }
}
