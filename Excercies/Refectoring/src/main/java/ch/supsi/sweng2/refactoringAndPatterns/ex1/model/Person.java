package ch.supsi.sweng2.refactoringAndPatterns.ex1.model;

public class Person  implements Component,VisitorNode {
    private String name;
    private String surname;
    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Override
    public void printAll() {
        System.out.println("Person: " + name + " " + surname);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitPerson(this);

    }

}
