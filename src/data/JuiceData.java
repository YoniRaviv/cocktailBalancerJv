package data;

public enum  JuiceData {

    Orange (0, 0,0),
    Pinapple   (0,0,0),
    Toniq (0,1.6,60),
    Lime  (0,1.6,6/100);


    private double Ethanol;
    private double Sugar;
    private double acid;

    JuiceData(double ethanol, double sugar, double acid) {
        Ethanol = ethanol;
        Sugar = sugar;
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