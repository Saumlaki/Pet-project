package ru.saumlaki.price_dynamic.view.main;


import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.saumlaki.price_dynamic.controllers.main.AbstractController;

public class MainView extends AbstractView {

    public FXMLLoader showForm(Stage currentStage) {
        return super.showForm(currentStage, null, "MainView", "Динамика цен", "Icon");
    }
}
