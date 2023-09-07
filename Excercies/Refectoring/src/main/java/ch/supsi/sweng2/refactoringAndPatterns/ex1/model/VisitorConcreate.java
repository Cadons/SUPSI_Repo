package ch.supsi.sweng2.refactoringAndPatterns.ex1.model;

public class VisitorConcreate implements Visitor {


    @Override
    public void visitPerson(Person person) {
        System.out.println("Person: " + person.getName() + " " + person.getSurname());
    }



    @Override
    public void visitDivision(Division division) {
        System.out.println("Division: " + division.getName());
        for (int i = 0; i < division.size(); i++) {
            ((VisitorNode) division.getChild(i)).accept(this);
        }
    }


}
