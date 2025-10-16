package exercise3;
import java.util.Scanner;
public class ProcessMortgage {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Mortgage [] mortgages = new Mortgage [3];

        System.out.println("Enter current prime interest rate:");
        double primeRate = input.nextDouble();
        input.nextLine();

        for (int i = 0; i < mortgages.length; i++ ) {
            System.out.println("***Enter details for mortgage #" + (i + 1));


            System.out.println("Enter mortgage number: ");
            String mortgageNumber = input.nextLine();

            System.out.println("Enter customer name: ");
            String customerName = input.nextLine();

            System.out.println("Enter mortgage amount: ");
            double amount = input.nextDouble();

            System.out.println("Enter term (1, 3 or 5 years): ");
            int term = input.nextInt();
            input.nextLine();

            System.out.println("Enter mortgage type (B for Business / P for Personal):");
            String type = input.nextLine().trim().toUpperCase();

            if (type.equals("B")) {
                mortgages[i] = new BusinessMortgage(mortgageNumber, customerName, amount, primeRate, term);
            } else if (type.equals("P")) {
                mortgages[i] = new PersonalMortgage(mortgageNumber, customerName, amount, primeRate, term);
            } else {
                System.out.println("Invalid type ");
                mortgages[i] = new PersonalMortgage(mortgageNumber, customerName, amount, primeRate, term);
            }
        }

        System.out.println("*** All Mortgages Information ***");
        for (Mortgage m : mortgages) {
            System.out.println(m.getMortgageInfo());
            System.out.println("-------------------------------------");


        }
        input.close();
    }
}
