package ch.supsi.sweng2.di;

public class Interior implements ICar{
    private String color;
    private double weight;

public Interior(){

}
    public String getColor() {
        return color;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
