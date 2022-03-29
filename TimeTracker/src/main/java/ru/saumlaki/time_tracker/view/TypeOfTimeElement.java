package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.controllers.TypeOfTimeController;
import ru.saumlaki.time_tracker.entity.TypeOfTime;

public class TypeOfTimeElement extends AbstractView{

    public void showForm(Stage stage, Stage parentStage, TypeOfTime element) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = super.showForm(stage, parentStage,element, "TypeOfTimeView", "Тип времени", null, "Icon");
//        ((TypeOfTimeController)fxmlLoader.getController()).setElement(element);
//        ((TypeOfTimeController)fxmlLoader.getController()).setStage(stage);
    }
}
