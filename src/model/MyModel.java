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

    public MyModel(double totalAbv, double totalSuger, double totalAcid, double dilution) {
        this.totalAbv = totalAbv;
        this.totalSuger = totalSuger;
        this.totalAcid = totalAcid;
        this.dilution = dilution;
    }

    public void calc(FinalCalc arg) {
        double finalAbv = 0;
        int totalVol = 0;
        double finalVol = 0;
        double finalSugar = 0;
        double finalAcid = 0;
        double sugarAcidBalance = 0;

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
        sugarAcidBalance = (finalSugar/finalAcid);

        //Final results
        System.out.println("\t"+"FINAL RESULTS");
        System.out.println("-----------------------");
        setChanged();
        notifyObservers("Dilution Rate: " + roundAndLimit(dilution) +
                        "\n" +"Final Volume: " + (int)finalVol + " ml. - "+ volRange(finalVol) +
                "\n" + "Final Abv%: " + roundAndLimit(finalAbv) + " - "+abvRange(finalAbv) +
                "\n" +"Final Sugar: " + roundAndLimit(finalSugar) +" - "+sugarRange(finalSugar) +
                "\n" +"Final Acidity: " + roundAndLimit(finalAcid) + " - "+acidRange(finalAcid) +
                "\n" +"Sugar to Acid Ratio: "+ new DecimalFormat("#.#").format(sugarAcidBalance));

    }

    //utility function to format the values to 2 decimal places and return with % sign
    private static String roundAndLimit(double value) {
        DecimalFormat df = new DecimalFormat("#.##%");
        return df.format(value);

    }


    //utility functions to check for ranges
    public String volRange(double value) {
        String message = null;
        if (value >= 130 && value <= 190) {
            message = "Very Good!";
        } else {
            message = "Please check your measures to correct the final volume!";
        }
        return message;
    }

    public String abvRange(double value) {
        String message = null;
        if (value*100 >= 15.0 && value*100 <= 29.0) {
            message = "Very good final ABV!";
        } else {
            message = "Please check your measures to correct the ABV% levels!";
        }
        return message;
    }

    public String sugarRange(double value) {
        String message = null;
        if (value*100 >= 3.7 && value*100 <= 8.9) {
            message = "Very good final sugar ratio!";
        } else {
            message = "Please check your sugar content!";
        }
        return message;
    }

    public String acidRange(double value) {
        String message = null;
        if (value*10 >= 0.0 && value*10 <= 0.94) {
            message = "Very good acidity levels!";
        } else {
            message = "Please correct your acid levels!";
        }
        return message;
    }

}
