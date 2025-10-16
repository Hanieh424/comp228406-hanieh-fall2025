package exercise2;

public class FullTimeGameTester extends GameTester{
    public FullTimeGameTester(String name) {
        super(name, true);
    }

    @Override
    public double determineSalary() {
        return 3000.0;
    }
}
