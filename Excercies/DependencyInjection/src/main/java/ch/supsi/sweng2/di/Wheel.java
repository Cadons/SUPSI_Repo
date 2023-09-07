package ch.supsi.sweng2.di;

import com.google.inject.Inject;

public class Wheel implements ICar{
    private double weight;
    @Inject
    private Tire tire;
    public Wheel(double w,Tire t)
    {
        this.tire=t;
        this.weight=w;
    }
    public double getWeight() {
        return weight+ tire.getWeight();
    }

    public Tire getTire() {
        return tire;
    }
}
