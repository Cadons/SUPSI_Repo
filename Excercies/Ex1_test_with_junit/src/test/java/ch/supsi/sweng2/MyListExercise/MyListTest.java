package ch.supsi.sweng2.MyListExercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    @Test
    void addElement() {
        MyList<Integer> list=new MyList<>();
        list.addElement(2);
        assertEquals(1,list.length());
    }

    @Test
    void length() {
        MyList<Integer> list=new MyList<>();

        assertEquals(0,list.length());
    }

    @Test
    void getElement() {
        MyList<Integer> list=new MyList<>();
        list.addElement(2);
        assertEquals(2,list.getElement(0));
        assertNull(list.getElement(2));
    }

    @Test
    void getForwardIterator() {
        MyList<Integer> list=new MyList<>();
        assertNotEquals(list.getForwardIterator(),list.getForwardIterator());
    }

    @Test
    void getBackwardIterator() {
        MyList<Integer> list=new MyList<>();
        assertNotEquals(list.getBackwardIterator(),list.getBackwardIterator());
    }
}