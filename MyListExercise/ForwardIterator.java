package ch.supsi.sweng2.MyListExercise;

import java.util.List;

public class ForwardIterator<T> implements MyIterator {

    private final MyList<T> myList;
    private int head;
    private int current;

    public ForwardIterator(MyList<T> l) {
        myList = l;
        head = 0;
        current = head;

    }

    @Override
    public void rewind() {
        current = head;
    }

    @Override
    public T nextElement() {
        if (current < myList.length()) {
            current++;
            return myList.getElement(current-1);
        } else {
            return null;
        }

    }

    @Override
    public boolean hasMoreElements() {
        return current<myList.length()-1;
    }
}
