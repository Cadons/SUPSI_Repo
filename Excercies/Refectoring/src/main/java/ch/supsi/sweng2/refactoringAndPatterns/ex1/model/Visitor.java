package ch.supsi.sweng2.refactoringAndPatterns.ex1.model;



public interface Visitor {



    void visitDivision(Division division);
    void visitPerson(Person person);
}