package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TotalTest {

    @Test
    public void getDrink() {
        Total ttl = new Total(1, 65);

        assertEquals("not Vodka", 1, ttl.getDrink());
    }

    @Test
    public void getMeasure() {
        Total ttl = new Total(1, 65);

        assertEquals("The measure isn't in the range", 65, ttl.getMeasure());
    }
}