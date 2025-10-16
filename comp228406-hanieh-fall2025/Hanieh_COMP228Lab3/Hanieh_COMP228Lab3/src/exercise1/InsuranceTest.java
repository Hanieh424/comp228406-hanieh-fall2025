package exercise1;
import java.util.Scanner;
public class InsuranceTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Insurance[] insurances = new Insurance[10];
        int count = 0;


        while (true) {
            System.out.print("Enter insurance type Health/Life or 'exit'" );
            String type = input.nextLine().trim();

            if (type.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Enter monthly cost: ");
            double cost = Double.parseDouble(input.nextLine());

            Insurance insurance = null;

            if (type.equalsIgnoreCase("Health")) {
                insurance = new Health("Health Insurance");
            } else if (type.equalsIgnoreCase("Life")) {
                insurance = new Life("Life Insurance");
            } else {
                System.out.println("Invalid insurance type. Try again");
                continue;

            }

            insurance.setInsuranceCost(cost);
            insurances[count++] = insurance;

            System.out.println("**** All Insurance Information ****");
            for (int i = 0; i < count; i++) {
                insurances[i].displayInfo();
                System.out.println();
            }

        }
    }
}
