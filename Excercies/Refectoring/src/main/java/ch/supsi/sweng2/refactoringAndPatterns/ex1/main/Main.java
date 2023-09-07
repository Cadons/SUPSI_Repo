package ch.supsi.sweng2.refactoringAndPatterns.ex1.main;

import ch.supsi.sweng2.refactoringAndPatterns.ex1.model.*;
import ch.supsi.sweng2.refactoringAndPatterns.ex1.modules.UsageTracerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class Main {
    public static void main(String[] args) {


        //create a tree structure of nodes and leaves (persons) and print it
        Node root = new Division("root");

        Node division1 = new Division("division1");
        Node division2 = new Division("division2");
        Node division3 = new Division("division3");

        root.add(division1);
        root.add(division2);
        root.add(division3);

        division1.add(new Person("person1", "surname1"));
        division1.add(new Person("person2", "surname2"));
        division1.add(new Person("person3", "surname3"));

        division2.add(new Person("person4", "surname4"));
        division2.add(new Person("person5", "surname5"));

        division3.add(new Person("person6", "surname6"));
        Injector injector = Guice.createInjector(new UsageTracerModule());
        Visitor visitor = injector.getInstance(VisitorConcreate.class);
        //root.printAll();
        ((VisitorNode)root).accept(visitor);
        Visitor visitor2 = new VisitorCounter();
        ((VisitorNode)root).accept(visitor2);
        System.out.println("visits:"+((VisitorCounter)visitor2).getCounter());;


    }
}