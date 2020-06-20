package data;

public enum AlcoholData{

    VODKA (0.4, 0,0),
    GIN   (0.4,0,0),
    WISKEY  (0.3,0,0),
    Rum (0.4, 0, 0),
    Campari (0.24, 24, 0),
    Syrup (0, 61.5,0),
    Pineapple   (0,12.4,0.08),
    Tonic (0,9,0),
    Lime  (0,1.6,0.6),
    Orange (0, 12.4, 0.08);


    private double Ethanol;
    private double Sugar;
    private double acid;

    AlcoholData(double ethanol, double sugar, double acid) {
        this.Ethanol = ethanol;
        this.Sugar = sugar;
        this.acid = acid;
    }

    public double getEthanol() {
        return Ethanol;
    }

    public double getSugar() {
        return Sugar;
    }

    public double getAcid() {
        return acid;
    }

}
