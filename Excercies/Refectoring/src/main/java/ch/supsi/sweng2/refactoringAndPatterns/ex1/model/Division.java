package ch.supsi.sweng2.refactoringAndPatterns.ex1.model;

import java.util.ArrayList;
import java.util.List;

public class Division implements Node,VisitorNode {
    private String name;
    private List<Component> components=new ArrayList<>();

    public Division(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printAll() {
        System.out.println("Division: " + name);
        for (Component component : components) {
            component.printAll();
        }
    }


    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public void remove(Node component) {
        components.remove(component);
    }

    @Override
    public Component getChild(int i) {
        return components.get(i);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitDivision(this);
    }
    public int size(){
        return components.size();
    }
}
