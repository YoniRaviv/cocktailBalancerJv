package model;

import data.DataBase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;

public class MyModel extends Observable implements Model {

    //Global vars used to calculate ranges and final values
    private double totalAbv = 0;
    private double totalSugar = 0;
    private double totalAcid = 0;
    private double dilution = 0;

//All user input calcs done here
    public void calc(FinalCalc arg) {
        double finalAbv = 0;
        int totalVol = 0;
        double finalVol = 0;
        double finalSugar = 0;
        double finalAcid = 0;
        double sugarAcidBalance = 0;

        //DB for all the data about the ingredients
        ArrayList<DataBase> drink = new ArrayList();

        drink.add(DataBase.values()[arg.getTotals().get(0).getDrink() -1]);
        drink.add(DataBase.values()[arg.getTotals().get(1).getDrink() -1]);
        drink.add(DataBase.values()[arg.getTotals().get(2).getDrink() -1]);

        //All the first values calculations for the final calculations
        totalVol = arg.getTotals().get(0).getMeasure()
                +arg.getTotals().get(1).getMeasure() + arg.getTotals().get(2).getMeasure();

        // sum all measure inputs from user
        for (int i =0; i< 3; i++){
            totalAbv += (arg.getTotals().get(i).getMeasure() * drink.get(i).getEthanol()) /totalVol;
            totalSugar += (arg.getTotals().get(i).getMeasure() * drink.get(i).getSugar()) /totalVol;
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
        finalSugar = finals.finalSugar(totalSugar, totalVol, finalVol);
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
                "\n" +"Sugar to Acid Ratio: "+ new DecimalFormat("#.#").format(sugarAcidBalance) +
                "\n" + recommendation(arg.getTotals().get(0).getDrink()));


    }

    //utility function to format the values to 2 decimal places and return with % sign
    public String roundAndLimit(double value) {
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
        if (value*10 >= 0.1 && value*10 <= 0.94) {
            message = "Very good acidity levels!";
        } else {
            message = "Please correct your acid levels!";
        }
        return message;
    }

    public String recommendation(int drink) {

        System.out.println("RECOMMENDED RECIPES:" + "\n" + "-------------------" + "\n");
        switch(drink) {
            case 1:
                System.out.println("Vodka based recipes:");
                System.out.println("1. Vodka Martini: " +
                        "\n" + "-90ml - Vodka"+
                        "\n" + "-30ml - dry vermouth"+
                        "\n" + "-1 - lemon peel" + "\n");
                System.out.println("2. Moscow Mule: " +
                        "\n" + "-60ml - Vodka"+
                        "\n" + "-60ml - ginger beer"+
                        "\n" + "-30ml - lime juice");
                break;
            case 2:
                System.out.println("Gin based recipes:");
                System.out.println("1. Gimlet: " +
                        "\n" + "-60ml - Gin"+
                        "\n" + "-30ml - simple syrup"+
                        "\n" + "-30ml - lime juice" + "\n");
                System.out.println("2. Negroni: " +
                        "\n" + "-30ml - Gin"+
                        "\n" + "-30ml - Campari"+
                        "\n" + "-30ml - Sweet vermouth");
                break;
            case 3:
                System.out.println("Wiskey based recipes:");
                System.out.println("1. Old Fashioned: " +
                        "\n" + "-60ml - Wiskey"+
                        "\n" + "-15ml - simple syrup"+
                        "\n" + "-3 dashes - Angostura bitters" + "\n");
                System.out.println("2. Sazerac: " +
                        "\n" + "-30ml - Rye whiskey"+
                        "\n" + "-30ml - Congac"+
                        "\n" + "-3 dashes - Angostura bitters");
                break;
            case 4:
                System.out.println("Rum based recipes:");
                System.out.println("1. Daquiri: " +
                        "\n" + "-45ml - White Rum"+
                        "\n" + "-15ml - simple syrup"+
                        "\n" + "-25ml - lime juice" + "\n");
                System.out.println("2. Pina colada: " +
                        "\n" + "-45ml - Rum"+
                        "\n" + "-30ml - pineapple juice"+
                        "\n" + "-25ml - coconut water");
                break;
            case 5:
                System.out.println("Campari based recipes:");
                System.out.println("1. Negroni: " +
                        "\n" + "-30ml - Gin"+
                        "\n" + "-30ml - Campari"+
                        "\n" + "-30ml - Sweet vermouth");
                System.out.println("2. Americano: " +
                        "\n" + "-45ml - Campari"+
                        "\n" + "-30ml - Sweet vermouth"+
                        "\n" + "-topped with club soda");
                break;
        }
        return "end";
    }
}
