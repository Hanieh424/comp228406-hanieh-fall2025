package exercise1;

abstract class Insurance {
    protected String typeOfInsurance;
    protected double monthlyCost;


    public Insurance(String typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;

    }


    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public double GetMonthlyCost() {
        return monthlyCost;
    }

    public abstract void setInsuranceCost(double cost);
    public abstract void displayInfo();

}
