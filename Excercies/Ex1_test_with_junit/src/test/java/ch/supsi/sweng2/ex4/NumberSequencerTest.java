package ch.supsi.sweng2.ex4;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
@RunWith(MockitoJUnitRunner.class)
class NumberSequencerTest {
    @Spy
    NumberTransformer numberTransformer=new CTransform();
    @Spy
    Printer printer=new CPrinter();;

    @Test
    void printNumbers() {
        openMocks(this);
        NumberSequencer numberSequencer = new NumberSequencer(numberTransformer,printer);
        numberSequencer.printNumbers(100);

        verify(numberTransformer, times(100)).transform(anyInt());
        verify(printer, times(100)).print(anyString());
        assertThrows(RuntimeException.class,()->when(numberTransformer.transform(0)).thenThrow(new RuntimeException("v must be >= 1")));

    }


}