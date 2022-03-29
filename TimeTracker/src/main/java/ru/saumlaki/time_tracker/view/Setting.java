package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.controllers.SettingController;

public class Setting extends  AbstractView{

    public void showForm(Stage stage, Stage parentStage) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = super.showForm(stage, parentStage, null,"SettingView", "Настройки", null, "Icon");
    }
}
