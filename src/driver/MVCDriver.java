package driver;

import model.MyModel;
import controller.*;
import model.Model;
import view.*;

public class MVCDriver {

    public static void main(String[] args) {

        Model model = new MyModel();

        View view = new MyCLIView(System.in, System.out);

        System.out.println("hello");
        Controller controller = new MyController(model, view);

        ((MyModel)model).addObserver(controller);

        /**
         * ADD view as observer to controller (uncomment your view)
         */

		((MyCLIView)view).addObserver(controller);

        view.start();
    }
}
