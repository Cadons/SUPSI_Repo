package org.example;

import org.example.model.CareTaker;
import org.example.model.Originator;

public class Main {
    public static void main(String[] args) {
        CareTaker careTaker = new CareTaker(new Originator("DATA"));
        System.out.println(careTaker.getValue());

        careTaker.doSomething();
        System.out.println(careTaker.getValue());
        careTaker.undo();
        System.out.println(careTaker.getValue());
        careTaker.doSomething();
        careTaker.doSomething();
        careTaker.doSomething();
        System.out.println(careTaker.getValue());
    }
}