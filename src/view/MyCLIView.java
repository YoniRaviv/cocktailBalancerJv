package view;


import model.FinalCalc;
import model.Total;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Scanner;


public class MyCLIView extends Observable implements View, Runnable{

    private Scanner input;
    private PrintWriter output;
    private FinalCalc finalCalc = new FinalCalc();

    int count = 0;


    public MyCLIView(InputStream in, OutputStream out) {
        input = new Scanner(in);
        output = new PrintWriter(out);
    }

    @Override
    public void run() {

        int clientInput = 0;

        while (clientInput != -1 && count!=3){
            System.out.println("\n"+"Select what type of ingredient you want to add:");
            System.out.println("[1] - Alcohol (base spirits that contain ethanol)");
            System.out.println("[2] - Juices & Mixers (ingredients with sugar and acid)");

            clientInput = input.nextInt();

            switch (clientInput){
                case 1: showAlcoholMenu();
                    count++;
                break;
                case 2: showJuiceMenu();
                    count++;
                break;
            }
        }

        System.out.println("\n"+"\t"+"\t"+"\t"+"\t"+"Finally select the preferred method:");
        System.out.println("\t"+"\t"+"\t"+"\t"+"-------------------------------------");
        System.out.println("1 - Shaken (with 120gr of ice for 20sec)");
        System.out.println("2 - Stirred (with 120gr of ice for 30sec)");

        int method = input.nextInt();
        finalCalc.setMethod(method);
        setChanged();
        notifyObservers(finalCalc);
    }


    private void showJuiceMenu() {
        System.out.println("\t"+"\t"+"\t"+"\t"+"Choose your juices & mixers:");
        System.out.println("\t"+"\t"+"\t"+"\t"+"-------------------------");

        System.out.println("[6] Simple Syrup");
        System.out.println("[7] Pineapple");
        System.out.println("[8] Tonic Water");
        System.out.println("[9] Lime Juice");
        System.out.println("[10] Orange");

        int juiceInput = input.nextInt();

        Integer measure = null;
        if (juiceInput > 5 && juiceInput < 11) {
            enterAndValidateMeasure(juiceInput);
        } else {
            System.out.println("Error: Not a correct value please enter between 6-10.");
        }

    }

    private void showAlcoholMenu() {

        System.out.println("\t"+"\t"+"\t"+"\t"+"Choose your base spirits:");
        System.out.println("\t"+"\t"+"\t"+"\t"+"-------------------------");

        System.out.println("[1] Vodka 40%");
        System.out.println("[2] Gin 40%");
        System.out.println("[3] Wiskey 42%");
        System.out.println("[4] Rum 40%");
        System.out.println("[5] Campari 22%");

        int alcoholInput = input.nextInt();

        Integer measure = null;
        if (alcoholInput > 0 && alcoholInput < 6) {
            enterAndValidateMeasure(alcoholInput);
        } else {
            System.out.println("Error: Not a correct value please enter between 1-5.");
        }

    }

    private void enterAndValidateMeasure(int alcoholInput) {
        Integer measure;
        System.out.print("Enter the volume(ml): ");

        measure = input.nextInt();

        if (measure > 0 && measure < 90) {
            finalCalc.getTotals().add(new Total(alcoholInput,measure));
        } else {
            System.out.println("Error: enter only values between 0-90 ml");
        }
    }


    @Override
    public void start() {
        new Thread(this).start();

    }

    @Override
    public void showResults(String s) {
        System.out.println(s);
    }

}
