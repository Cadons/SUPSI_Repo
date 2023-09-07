package ch.supsi.sweng2.MyListExercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForwardIteratorTest {

    @Test
    void rewind() {
        MyList<Integer> list = new MyList<>();
        list.addElement(1);
        list.addElement(2);
        list.addElement(3);
        var fIter = list.getForwardIterator();
        fIter.nextElement();
        fIter.nextElement();
        fIter.rewind();

        assertEquals(1, fIter.nextElement());

    }

    @Test
    void nextElement() {
        MyList<Integer> list = new MyList<>();
        list.addElement(1);
        list.addElement(2);

        var fIter = list.getForwardIterator();
        assertEquals(1, fIter.nextElement());
        assertEquals(2, fIter.nextElement());
    }

    @Test
    void hasMoreElements() {
        MyList<Integer> list = new MyList<>();
        list.addElement(1);
        list.addElement(2);

        var fIter = list.getForwardIterator();
        assertTrue(fIter.hasMoreElements());
        list = new MyList<>();
        list.addElement(1);


        fIter = list.getForwardIterator();
        assertFalse(fIter.hasMoreElements());
    }
}