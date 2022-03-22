package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.controllers.TypeOfTimeController;
import ru.saumlaki.time_tracker.entity.TypeOfTime;

public class TypeOfTimeElement extends AbstractView{

    public void showForm(Stage stage, TypeOfTime element) {

        FXMLLoader fxmlLoader = super.showForm(stage, "TypeOfTimeView", "Тип времени", null, null);
        ((TypeOfTimeController)fxmlLoader.getController()).setElement(element);
    }
}
