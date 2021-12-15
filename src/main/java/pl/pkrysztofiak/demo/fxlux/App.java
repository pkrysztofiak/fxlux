package pl.pkrysztofiak.demo.fxlux;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Panel panel1 = new Panel(new Bounds(0., 0., 0.5, 1.));
        Panel panel2 = new Panel(new Bounds(0.5, 0., 0.5, 1.));

        Grid grid = new Grid(panel1, panel2);



    }
}
