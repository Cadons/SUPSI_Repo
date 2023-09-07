package ch.supsi.sweng2.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarTest {
    @Test
    void getWeightInjected() {

        Injector injector= Guice.createInjector(new CarModuleTest(new WheelModule()));
        ICar car= injector.getInstance(ICar.class);
        Assertions.assertEquals(540., car.getWeight());
    }
}
