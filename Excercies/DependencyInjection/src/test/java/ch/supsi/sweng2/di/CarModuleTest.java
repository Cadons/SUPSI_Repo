package ch.supsi.sweng2.di;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;

public class CarModuleTest extends AbstractModule {
    private AbstractModule tireModule;
    public CarModuleTest(AbstractModule tireModule)
    {
        this.tireModule=tireModule;
    }
    protected void configure(){
        bind(IEngine.class).to(FakeEngine.class);
        bind(IBody.class).to(FakeBody.class);
        bind(ICar.class).to(Car.class);
    }
    @Provides
    Wheel[] provideWheels(){
        Wheel[] wheels=new Wheel[4];
        Injector injector= Guice.createInjector(tireModule);

        wheels[0]=new Wheel(20,injector.getInstance(Tire.class));
        wheels[1]=new Wheel(20,injector.getInstance(Tire.class));
        wheels[2]=new Wheel(20,injector.getInstance(Tire.class));
        wheels[3]=new Wheel(20,injector.getInstance(Tire.class));
        return wheels;
    }
}
