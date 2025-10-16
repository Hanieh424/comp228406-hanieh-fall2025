package exercise1;

class Health extends Insurance {

    public Health(String typeOfInsurance) {
        super(typeOfInsurance);
    }
    @Override
    public void setInsuranceCost(double cost) {
        monthlyCost = cost;
    }

    @Override
    public void displayInfo() {
        System.out.println("*** Health Insurance ***");
        System.out.println("Type: " + typeOfInsurance);
        System.out.println("Monthly Cost: $" + monthlyCost);
    }
}
