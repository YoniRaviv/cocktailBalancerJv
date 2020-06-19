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


//    public void setOutput(PrintWriter output) {
//        this.output = output;
//    }

    public MyCLIView(InputStream in, OutputStream out) {
        input = new Scanner(in);
        output = new PrintWriter(out);
    }

    @Override
    public void run() {

        int clientInput = 0;
        double num = 0.218181818187;

        while (clientInput != -1 && count!=3){

            System.out.println("Pls enter ");
            System.out.println("1 - Alcohol");
            System.out.println("2 - Juice");

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

        System.out.println("Pls enter method");
        System.out.println("1 - shaken");
        System.out.println("2 - stired");

        int method = input.nextInt();
        finalCalc.setMethod(method);
        setChanged();
        notifyObservers(finalCalc);

    }


    private void showJuiceMenu() {
        System.out.println("menu 2");

        System.out.println("[4] syrup");
        System.out.println("[5] Pinapple");
        System.out.println("[6] Toniq");
        System.out.println("[7] Lime");

        int juiceInput = input.nextInt();

        Integer measure = null;
        if (juiceInput > 3 && juiceInput < 8) {


            System.out.println("Pls enter vol: ");
            measure = input.nextInt();

            if (measure > 0 && measure < 90) {
//                getIngredient(alcoholInput,measure);
                finalCalc.getTotals().add(new Total(juiceInput,measure));
            }

        }
    }

    private void showAlcoholMenu() {

        System.out.println("menu 1");

        System.out.println("[1] Vodka");
        System.out.println("[2] gin");
        System.out.println("[3] wiskey");

        int alcoholInput = input.nextInt();

        Integer measure = null;
        if (alcoholInput > 0 && alcoholInput < 5) {

            System.out.println("Pls enter vol: ");
            measure = input.nextInt();

            if (measure > 0 && measure < 90) {
                finalCalc.getTotals().add(new Total(alcoholInput,measure));
            }

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
