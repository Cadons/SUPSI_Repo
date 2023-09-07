package Lesson1;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class MoneyTest {
    @Test
    public void testEquals() {
        Money m12CHF = new Money(12, "CHF"); // (*)
        Money m14CHF = new Money(14, "CHF");
        assertEquals(m12CHF, new Money(12, "CHF"));
        assertFalse(m12CHF.equals(m14CHF));
    }
}
