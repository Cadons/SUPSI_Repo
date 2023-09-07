package ch.supsi.sweng2.di;

public class Body implements IBody,ICar{
    private double weight;
    private String color;

    public Body(){

    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public double getWeight() {
        return weight;
    }
}
