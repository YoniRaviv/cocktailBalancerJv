package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyModelTest {

    @Before
    public void setUp() throws Exception {
        FinalCalc finals = new FinalCalc();
    }

    @Test
    public void calc() {
    }

    @Test
    public void volRange() {
        double finalVol = (40*1.54)+120;
        assertEquals("not good vol", 180, finalVol, 5.5);
        assertTrue("final vol is out of range: " + "130-190", 130 <= finalVol && finalVol <= 190);
    }

    @Test
    public void abvRange() {
        FinalCalc finals = new FinalCalc();
        double finalAbv = finals.finalAbv(22.9, 105, 159);
        assertTrue("final ABV is out of range: " + "15-29", 15.0 <= finalAbv && finalAbv <= 29.0);
    }

    @Test
    public void sugarRange() {
    }

    @Test
    public void acidRange() {
    }
}