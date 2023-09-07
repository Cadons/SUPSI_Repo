package ch.supsi.sweng2.di;

import com.google.inject.Inject;

public class Car implements ICar{
    @Inject
    private IEngine engine;
    @Inject
    private IBody body;
    @Inject
    private Frame frame;
    @Inject
    private Interior interior;
    @Inject
    private Wheel[] wheels;
    public Car() {
     //   engine = new Engine();
     //   body = new Body();
    }
    public double getWeight() {
        double tmp= ((ICar)engine).getWeight()+((ICar)body).getWeight()+((ICar)frame).getWeight()+((ICar)interior).getWeight();
        for(Wheel w : wheels)
        {
            tmp+=((ICar)w).getWeight();
        }
        return tmp;
    }
}
