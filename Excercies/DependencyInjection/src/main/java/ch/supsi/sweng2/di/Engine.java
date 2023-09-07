package ch.supsi.sweng2.di;

public class Engine implements IEngine,ICar{
    private double weight;
    private int cylinders;
    private double power;


    public Engine(){

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
