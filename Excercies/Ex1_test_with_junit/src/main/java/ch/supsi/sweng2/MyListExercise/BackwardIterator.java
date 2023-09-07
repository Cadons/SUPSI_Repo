package ch.supsi.sweng2.MyListExercise;

public class BackwardIterator <T> implements MyIterator{
    private final MyList<T> myList;
    private int head;
    private int current;
    public BackwardIterator(MyList<T> l){
        myList=l;
        head=l.length()-1;
        current=head;
    }
    @Override
    public void rewind() {
        current=head;
    }

    @Override
    public T nextElement() {

        if (current >= 0) {
            current--;
            return myList.getElement(current+1);
        } else {
            return null;
        }
    }

    @Override
    public boolean hasMoreElements() {

        return current!=0;
    }
}
