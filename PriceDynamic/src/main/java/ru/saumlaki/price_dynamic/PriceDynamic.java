package ru.saumlaki.price_dynamic;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.saumlaki.price_dynamic.view.main.MainView;

public class PriceDynamic extends Application{

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) {

        new MainView().showForm();
    }
}
