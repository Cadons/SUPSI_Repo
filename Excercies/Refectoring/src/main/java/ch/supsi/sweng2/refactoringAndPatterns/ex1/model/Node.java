package ch.supsi.sweng2.refactoringAndPatterns.ex1.model;

public interface Node extends Component {
    //operation

    void add(Component component);
    void remove(Component component);


    void remove(Node component);

    Component getChild(int i);
}
