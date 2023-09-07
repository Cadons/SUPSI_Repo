package ch.supsi.sweng2.ex1;

import ch.supsi.sweng2.ex1.SequenceCache;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;


public class SequenceCacheTest {
    @Spy
    private SequenceCache cache;
    @Test
    public void lengthTest() throws Exception {
        openMocks(this);

   /*     SequenceCache sc=new SequenceCache();
        assertEquals(6,sc.length(10));
        assertThrows(Exception.class,()->sc.length(2));*/
        cache.length(10);
        verify(cache).createWorker(10);
        cache.length(10);
        verify(cache,times(1)).createWorker(10);
    }
}
