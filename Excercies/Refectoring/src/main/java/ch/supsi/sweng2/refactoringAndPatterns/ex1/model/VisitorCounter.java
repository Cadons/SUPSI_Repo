package ch.supsi.sweng2.refactoringAndPatterns.ex1.model;

public class VisitorCounter implements Visitor{
    private int counter=0;
    @Override
    public void visitDivision(Division division) {
        counter++;
        for (int i = 0; i < division.size(); i++) {
            ((VisitorNode) division.getChild(i)).accept(this);
        }
    }

    @Override
    public void visitPerson(Person person) {
        this.counter++;
    }

    public int getCounter() {
        return counter;
    }
}
