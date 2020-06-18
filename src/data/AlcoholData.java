package data;

public enum AlcoholData{

    VODKA (0.4, 0,0),
    GIN   (0.5,0,0),
    WISKEY  (0.3,0,0),
    Orange (0, 0,0),
    Pinapple   (0,0,0),
    Toniq (0,1.6,60),
    Lime  (0,1.6,0.6);


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
