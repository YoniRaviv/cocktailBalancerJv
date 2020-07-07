package controller;

import model.FinalCalc;
import model.Model;
import model.MyModel;
import view.View;
import java.util.Observable;

public class MyController implements Controller {

    private Model model;
    private View view;

    public MyController(Model model, View view) {
        this.view = view;
        this.model = model;
    }
    //updates the view from model and vice versa
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof View) {
            ((MyModel) model).calc((FinalCalc) arg);
        } else if (o instanceof MyModel) {
            view.showResults((String) arg);
        }
    }

}
