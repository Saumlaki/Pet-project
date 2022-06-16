package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.controllers.MainController;

public class Main extends AbstractView{

    public void showForm(Stage stage, Stage parentStage) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = super.showForm(stage, parentStage, null, "MainView", "Учет времени", "chart.css", "Icon");
       ((MainController)fxmlLoader.getController()).setStage(stage);


    }
}
