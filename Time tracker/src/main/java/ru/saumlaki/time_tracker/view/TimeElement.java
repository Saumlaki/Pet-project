package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.controllers.TimeController;
import ru.saumlaki.time_tracker.entity.Time;

public class TimeElement extends AbstractView{

    public void showForm(Stage stage, Time element) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = super.showForm(stage, "TimeView", "Тип времени", null, null);
        ((TimeController)fxmlLoader.getController()).setElement(element);
        ((TimeController)fxmlLoader.getController()).setStage(stage);
    }
}
