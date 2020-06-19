package model;

import java.util.ArrayList;

public class FinalCalc {

    ArrayList<Total> totals = new ArrayList<>();
    int method;

    public ArrayList<Total> getTotals() {
        return totals;
    }

    public void setTotals(ArrayList<Total> totals) {
        this.totals = totals;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }
    public double finalAbv(double totalAbv, double totalVol, double finalVol) {
        return ((totalAbv*totalVol)/finalVol);
    }

    public double finalSugar(double totalSugar, double totalVol, double finalVol) {
        return ((totalSugar * totalVol)/finalVol)/100;

    }

    public double finalAcid(double totalAcid, double totalVol, double finalVol) {
        return ((totalAcid*totalVol)/finalVol)/10;
    }
}
