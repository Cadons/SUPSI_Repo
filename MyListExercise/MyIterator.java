package ch.supsi.sweng2.MyListExercise;

public interface MyIterator<T>{
    void rewind();
    T nextElement();
    boolean hasMoreElements();
}
