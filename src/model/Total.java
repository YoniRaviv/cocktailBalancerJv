package model;

public class Total {

    //class for storing the user inputs of chosen drink and its mesuare
    private int drink;
    private int measure;

    public Total(int drink, int measure) {
        this.drink = drink;
        this.measure = measure;
    }

    public int getDrink() {
        return drink;
    }

    public void setDrink(int drink) {
        this.drink = drink;
    }

    public int getMeasure() {
        return measure;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }
}
