package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.controllers.MainController;
import ru.saumlaki.time_tracker.supporting.TimerWatch;

public class Main extends AbstractView{

    public void showForm(Stage stage, TimerWatch timerWatch) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = super.showForm(stage, "MainView", "Учет времени", "chart.css", "Icon");
        ((MainController)fxmlLoader.getController()).setElement(timerWatch);
        ((MainController)fxmlLoader.getController()).setStage(stage);

        timerWatch.setMainController(fxmlLoader.getController());
    }
}
