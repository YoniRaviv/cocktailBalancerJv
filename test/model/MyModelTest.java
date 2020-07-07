package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyModelTest {



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
        FinalCalc finals = new FinalCalc();
        double finalSugar = finals.finalSugar(12.4, 105, 159);
        assertTrue("final Sugar is out of range: " + "3.7-8.9", 3.7 <= finalSugar*100 && finalSugar*100 <= 8.9);
    }

    @Test
    public void acidRange() {
        FinalCalc finals = new FinalCalc();
        double finalAcid = finals.finalAcid(1.2, 105, 159);
        System.out.println(finalAcid*10);
        assertTrue("final Sugar is out of range: " + "0.1-0.94", 0.1 <= finalAcid*10 && finalAcid*10 <= 0.94);

    }
}