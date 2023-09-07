package ch.supsi.sweng2.di;

public class FakeBody implements IBody,ICar{
    private double weight=40;
    private String color;

    public FakeBody(){

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
