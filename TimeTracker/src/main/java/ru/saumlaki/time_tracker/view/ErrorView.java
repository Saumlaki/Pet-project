package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.controllers.ErrorController;
import ru.saumlaki.time_tracker.supporting.Error;

public class ErrorView extends AbstractView{
    public void showForm(Stage stage, Stage parentStage, Error element) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = super.showForm(stage, parentStage, element, "ErrorView", "Ошибка выполнения программы", null, "Icon");
        ((ErrorController) fxmlLoader.getController()).setElement(element);
        ((ErrorController) fxmlLoader.getController()).setStage(stage);
    }
}
