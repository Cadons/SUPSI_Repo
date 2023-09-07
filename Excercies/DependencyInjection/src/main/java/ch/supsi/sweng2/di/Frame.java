package ch.supsi.sweng2.di;

public class Frame implements ICar{
    private double weight;
    private long number;
public Frame(){

}

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getNumber() {
        return number;
    }

    public double getWeight() {
        return weight;
    }
}
