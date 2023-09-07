package ch.supsi.sweng2.di;

public class FakeEngine implements IEngine,ICar{
    private double weight=400;
    private int cylinders;
    private double power;


    public FakeEngine(){

    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getWeight() {
        return weight;
    }

    public int getCylinders() {
        return cylinders;
    }

    public double getPower() {
        return power;
    }
}
