package exercise1;

class Life extends Insurance {

    public Life(String typeOfInsurance) {
       super(typeOfInsurance) ;
    }

    @Override
    public void setInsuranceCost(double cost) {
        monthlyCost = cost;
    }

    @Override
    public void displayInfo() {
        System.out.println("*** Life Insurance ***");
        System.out.println("Type: " + typeOfInsurance);
        System.out.println("Monthly Cost: $" + monthlyCost);
    }

}
