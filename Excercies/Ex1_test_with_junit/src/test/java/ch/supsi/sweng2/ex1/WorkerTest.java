package ch.supsi.sweng2.ex1;

import ch.supsi.sweng2.ex1.Worker;
import org.junit.jupiter.api.Test;


import javax.swing.tree.ExpandVetoException;

import static org.junit.jupiter.api.Assertions.*;


public class WorkerTest {


    @Test
    public void testConversion(){
        assertEquals(2.,Worker.conversion(4));
        assertEquals(10.,Worker.conversion(3));
    }
    @Test
    public void testSequence() {
        var worker=new Worker(10);
        try{
            assertEquals(6,worker.sequence());
        }catch (Exception e){
            fail();
        }

        worker=new Worker(2);

        Worker finalWorker = worker;
        assertThrows(Exception.class,()-> finalWorker.sequence());
    }
}
