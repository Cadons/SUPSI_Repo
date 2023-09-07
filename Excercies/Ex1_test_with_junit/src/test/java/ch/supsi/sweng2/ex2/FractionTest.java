package ch.supsi.sweng2.ex2;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class FractionTest {
    @Test
    public void sumTest() {
        try {
            var f = new Fraction(1, 2);
            // assertEquals(new Fraction(1,1),f.sum(f));
            var f2 = new Fraction(1, 1);

            assertEquals(new Fraction(3, 2), f.sum(f2));
        } catch (Exception e) {
            fail();
        }
        assertThrows(Exception.class,()->new Fraction(1,0));
    }

    @Test
    public void subtractTest() {
        try {
            var f = new Fraction(3, 2);
            // assertEquals(new Fraction(1,1),f.sum(f));
            var f2 = new Fraction(1, 1);
            assertEquals(new Fraction(1, 2), f.subtract(f2));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void multiplyTest() {
        try {
            var f = new Fraction(1, 2);
            // assertEquals(new Fraction(1,1),f.sum(f));
            var f2 = new Fraction(1, 1);
            assertEquals(new Fraction(1, 2), f.multiply(f2));
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void divideTest() {
        try {
            var f = new Fraction(1, 1);
            // assertEquals(new Fraction(1,1),f.sum(f));
            var f2 = new Fraction(1, 2);
            assertEquals(new Fraction(2, 1), f.divide(f2));
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void simplifyTest() {
        try {
            var f2 = new Fraction(8, 12);
            assertEquals(new Fraction(2, 3), f2.semplify(f2));
        } catch (Exception e) {
            fail();
        }

    }
}
