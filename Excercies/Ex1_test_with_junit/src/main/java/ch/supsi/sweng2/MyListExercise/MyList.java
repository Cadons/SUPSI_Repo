package ch.supsi.sweng2.MyListExercise;

import java.util.ArrayList;

public class MyList<T> {
    private ArrayList<T> myList = new ArrayList<>();

    public MyList() {
    }

    public void addElement(T ob) {

        myList.add(ob);
    }

    public int length() {
        return myList.size();
    }

    public T getElement(int pos) {
        if (pos < myList.size() && pos >= 0)
            return myList.get(pos);
        else
            return null;
    }

    public MyIterator<T> getForwardIterator() {
        return new ForwardIterator<T>(this);
    }

    public MyIterator<T> getBackwardIterator() {
        return new BackwardIterator<T>(this);
    }


}
