package ch.supsi.sweng2.di;

import com.google.inject.AbstractModule;

public class WheelModule extends AbstractModule {
    protected void configure(){
       bind(Tire.class).to(WinterTire.class);
    }
}
