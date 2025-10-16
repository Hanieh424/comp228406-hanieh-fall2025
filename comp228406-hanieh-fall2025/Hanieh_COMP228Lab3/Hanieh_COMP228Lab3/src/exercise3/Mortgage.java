package exercise3;

public abstract class Mortgage implements MortgageConstants {
    protected String mortgageNumber;
    protected String customerName;
    protected double amount;
    protected double interestRate;
    protected int term;

    public Mortgage(String mortgageNumber, String customerName, double amount, double interestRate, int term) {
        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;


        if (amount > MAX_MORTGAGE_AMOUNT) {
            System.out.println("Amount exceeds max limit $ + MAX_MORTGAGE_AMOUNT");
            this.amount = MAX_MORTGAGE_AMOUNT;
        } else {
            this.amount = amount;
        }

        this.interestRate = interestRate;

        if (term != SHORT_TERM && term != MEDIUM_TERM && term != LONG_TERM) {
            System.out.println("Invalid term. Setting to short term");
            this.term = SHORT_TERM;
        }
        this.term = term;

    }


    public abstract double getTotalAmountOwed();


    public String getMortgageInfo() {


        return "\nBank: " + BANK_NAME +
                "\nMortgage Number: " + mortgageNumber +
                "\nCustomer Name: " + customerName +
                "\nAmount: $" + String.format("%.2f", amount) +
                "\nInterest Rate: " + String.format("%.2f", interestRate) + "%" +
                "\nTerm: " + term + " year(s)" +
                "\nTotal Amount Owed: $" + String.format("%.2f", getTotalAmountOwed());
    }
}



