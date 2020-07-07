package model;

import static org.junit.Assert.*;

public class FinalCalcTest {

    @org.junit.Test
    public void finalAbv() {
        double finalAbv = (0.229*105)/159.5;
        assertEquals("not equal", 0.15, finalAbv,0.8);
    }

    @org.junit.Test
    public void finalSugar() {
        double finalSugar = ((4 * 105)/159.5)/100;
        assertEquals("not equal", 0.3, finalSugar,0.5);

    }

    @org.junit.Test
    public void finalAcid() {
        double finalAcid = ((0.109*105)/159.5);
        assertEquals("not equal", 0.07, finalAcid,0.03);
    }
}