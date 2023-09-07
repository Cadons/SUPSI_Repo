package ch.supsi.sweng2.MyListExercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackwardIteratorTest {

    @Test
    void rewind() {
        MyList<Integer> list = new MyList<>();
        list.addElement(1);
        list.addElement(2);
        list.addElement(3);
        var fIter = list.getBackwardIterator();
        fIter.nextElement();
        fIter.nextElement();
        fIter.rewind();

        assertEquals(3, fIter.nextElement());

    }

    @Test
    void nextElement() {
        MyList<Integer> list = new MyList<>();
        list.addElement(1);
        list.addElement(2);

        var fIter = list.getBackwardIterator();
        assertEquals(2, fIter.nextElement());
        assertEquals(1, fIter.nextElement());
    }

    @Test
    void hasMoreElements() {
        MyList<Integer> list = new MyList<>();
        list.addElement(1);
        list.addElement(2);

        var fIter = list.getBackwardIterator();
        assertTrue(fIter.hasMoreElements());
        list = new MyList<>();
        list.addElement(1);

        fIter = list.getBackwardIterator();
        assertFalse(fIter.hasMoreElements());
    }
}