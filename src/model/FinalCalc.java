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
}
