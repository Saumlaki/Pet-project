package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.controllers.DataOfTimeElementController;
import ru.saumlaki.time_tracker.entity.DataOfTime;

public class DataOfTimeElement extends AbstractView {

    public void showForm(Stage stage, DataOfTime element) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = super.showForm(stage, "DataOfTimeView", "Данные по затратам времени", null, null);
        ((DataOfTimeElementController) fxmlLoader.getController()).setElement(element);
        ((DataOfTimeElementController) fxmlLoader.getController()).setStage(stage);
    }
}
