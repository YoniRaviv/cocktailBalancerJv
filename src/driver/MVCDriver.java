package driver;

import model.MyModel;
import controller.*;
import model.Model;
import view.*;

public class MVCDriver {

    public static void main(String[] args) {

        Model model = new MyModel();

        View view = new MyCLIView(System.in, System.out);

        System.out.println("\n" +"\t" +"\t" +"\t" +"\t" + "WELCOME TO THE COCKTAIL BALANCER.");
        System.out.println("\t" +"\t" +"\t" +"\t" + "#################################");
        Controller controller = new MyController(model, view);

        ((MyModel)model).addObserver(controller);


		((MyCLIView)view).addObserver(controller);

        view.start();
    }
}
