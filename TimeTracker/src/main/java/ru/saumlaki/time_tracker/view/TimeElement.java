package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.controllers.TimeController;
import ru.saumlaki.time_tracker.entity.Time;

public class TimeElement extends AbstractView{

    public void showForm(Stage stage, Stage parentStage, Time element) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = super.showForm(stage, parentStage, element, "TimeView", "Тип времени", null, "Icon");
//        ((TimeController)fxmlLoader.getController()).setElement(element);
//        ((TimeController)fxmlLoader.getController()).setStage(stage);
    }
}
