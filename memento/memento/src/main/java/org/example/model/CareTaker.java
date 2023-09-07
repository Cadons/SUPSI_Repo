package org.example.model;

import java.util.Stack;

public class CareTaker {
    private Stack<Originator.Memento> mementos = new Stack<>();
    private Originator originator;
    public CareTaker(Originator originator) {
        this.originator = originator;

    }
    public void doSomething(){
        mementos.push(originator.save());
        originator.setValue("A");
    }
    public void undo(){
        if(mementos.size()>0){

            originator.restore(  mementos.pop());
        }
    }
    public String getValue() {
        return originator.getValue();
    }
}
