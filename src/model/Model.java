package model;

public interface Model {
    void calc(FinalCalc arg);
    String roundAndLimit(double value);
    String volRange(double value);
    String abvRange(double value);
    String sugarRange(double value);
    String acidRange(double value);
}
