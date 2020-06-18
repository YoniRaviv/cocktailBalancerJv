package model;

import data.AlcoholData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;

public class MyModel extends Observable implements Model {


    public void calc(FinalCalc arg) {

        ArrayList<AlcoholData> drink = new ArrayList();

        drink.add(AlcoholData.values()[arg.getTotals().get(0).getDrink() - 1]);
        drink.add(AlcoholData.values()[arg.getTotals().get(1).getDrink() -1]);
        drink.add(AlcoholData.values()[arg.getTotals().get(2).getDrink() -1]);

        int totalVol = arg.getTotals().get(0).getMeasure()
                +arg.getTotals().get(1).getMeasure() + arg.getTotals().get(2).getMeasure();
        double totalAbv =0;
        double totalSuger =0;
        double totalAcid =0;

        for (int i =0; i< 3; i++){
            totalAbv += (arg.getTotals().get(i).getMeasure() * drink.get(i).getEthanol()) /totalVol;
            totalSuger += (arg.getTotals().get(i).getMeasure() * drink.get(i).getSugar()) /totalVol;
            totalAcid += (arg.getTotals().get(i).getMeasure() * drink.get(i).getAcid()) /totalVol;
        }

        double dilution = 0;

        if (arg.getMethod() == 1){
            dilution = ((-1.567 * totalAbv*totalAbv) + (1.742 * totalAbv) ) + 0.203;
        }else if(arg.getMethod() == 2){
            dilution = ((-1.21 * totalAbv*totalAbv) + (1.246 * totalAbv) ) + 0.145;
        }

        setChanged();
        notifyObservers("Dilution: " + roundAndLimit(dilution) +
                "\n" + "totalAbv: " + roundAndLimit(totalAbv) +
                "\n" +"total suger: " + roundAndLimit(totalSuger/100) +
                "\n" +"total acid: " + roundAndLimit(totalAcid/10) +
                "\n" +"total vol: " + totalVol);


    }

    //function to format the calcs to 2 decimal places
    private static String roundAndLimit(double value) {

        DecimalFormat df = new DecimalFormat("#.#%");
        return df.format(value);

    }


}
