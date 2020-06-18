package model;

public abstract class Ingredient {

    private String name;
    private double suger;
    private double acid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSuger() {
        return suger;
    }

    public void setSuger(double suger) {
        this.suger = suger;
    }

    public double getAcid() {
        return acid;
    }

    public void setAcid(double acid) {
        this.acid = acid;
    }
}
