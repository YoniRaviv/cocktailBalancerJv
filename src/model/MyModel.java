package model;

import data.AlcoholData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;

public class MyModel extends Observable implements Model {

    private double totalAbv = 0;
    private double totalSuger = 0;
    private double totalAcid = 0;
    private double dilution = 0;
    private double finalAbv = 0;
    private int totalVol = 0;
    private double finalVol = 0;
    private double finalSugar = 0;
    private double finalAcid = 0;
    private double sugarAcidBalance = 0;


    public void calc(FinalCalc arg) {

        //DB for all the data about the ingredients
        ArrayList<AlcoholData> drink = new ArrayList();

        drink.add(AlcoholData.values()[arg.getTotals().get(0).getDrink() -1]);
        drink.add(AlcoholData.values()[arg.getTotals().get(1).getDrink() -1]);
        drink.add(AlcoholData.values()[arg.getTotals().get(2).getDrink() -1]);
        //All the first values calculations for the final calculations
        totalVol = arg.getTotals().get(0).getMeasure()
                +arg.getTotals().get(1).getMeasure() + arg.getTotals().get(2).getMeasure();

        // sum all measure inputs from user
        for (int i =0; i< 3; i++){
            totalAbv += (arg.getTotals().get(i).getMeasure() * drink.get(i).getEthanol()) /totalVol;
            totalSuger += (arg.getTotals().get(i).getMeasure() * drink.get(i).getSugar()) /totalVol;
            totalAcid += (arg.getTotals().get(i).getMeasure() * drink.get(i).getAcid()) /totalVol;
        }

        // dilution calculations
        if (arg.getMethod() == 1){
            dilution = ((-1.567 * totalAbv*totalAbv) + (1.742 * totalAbv) ) + 0.203;
        }else if(arg.getMethod() == 2){
            dilution = ((-1.21 * totalAbv*totalAbv) + (1.246 * totalAbv) ) + 0.145;
        }

        //all final calculations for range checks and final results
        FinalCalc finals = new FinalCalc();
        finalVol = (totalVol*dilution)+totalVol;

        finalAbv = finals.finalAbv(totalAbv, totalVol, finalVol);
        finalSugar = finals.finalSugar(totalSuger, totalVol, finalVol);
        finalAcid = finals.finalAcid(totalAcid, totalVol, finalVol);
        sugarAcidBalance = (finalSugar*finalAcid)/100;

        //check ranges of calculations
        volRange(finalVol);
        abvRange(finalAbv);
        sugarRange(finalSugar);
        acidRange(finalAcid);


        setChanged();
        notifyObservers("Dilution: " + roundAndLimit(dilution) +
                "\n" + "final Abv: " + roundAndLimit(finalAbv) +
                "\n" +"total sugar: " + roundAndLimit(finalSugar) +
                "\n" +"total acid: " + roundAndLimit(finalAcid) +
                "\n" +"final vol: " + (int)finalVol + sugarAcidBalance);


    }

    //utility function to format the values to 2 decimal places and return with % sign
    private static String roundAndLimit(double value) {

        DecimalFormat df = new DecimalFormat("#.##%");
        return df.format(value);

    }

    //utility functions to check for ranges
    private void volRange(double value) {
        System.out.println(value);
        if (value >= 130 && value <= 190) {
            setChanged();
            notifyObservers("Very good final volume!");
        } else {
            setChanged();
            notifyObservers("Please check your measures to correct the final volume!");
        }
    }

    private void abvRange(double value) {
        System.out.println(value);
        if (value*100 >= 16.0 && value*100 <= 29.0) {
            setChanged();
            notifyObservers("Very good final ABV!");
        } else {
            setChanged();
            notifyObservers("Please check your measures to correct the ABV% levels!");
        }
    }

    private void sugarRange(double value) {
        System.out.println(value);
        if (value*100 >= 3.7 && value*100 <= 8.9) {
            setChanged();
            notifyObservers("Very good final sugar ratio!");
        } else {
            setChanged();
            notifyObservers("Please check your sugar content!");
        }
    }

    private void acidRange(double value) {

        if (value*10 >= 0.0 && value*10 <= 0.94) {
            setChanged();
            notifyObservers("Very good acidity levels");
        } else {
            setChanged();
            notifyObservers("Please correct your acid levels!");
        }
    }


}
