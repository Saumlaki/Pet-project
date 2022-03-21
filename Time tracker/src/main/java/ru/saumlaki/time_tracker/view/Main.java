package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.DialogMessengerElementForm;
import ru.saumlaki.time_tracker.TimeTracker;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Main {

    public void showElementForm(Stage stage) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = null;
        try {

            fxmlLoader = new FXMLLoader(new URL("file:/" + new File(TimeTracker.getPropertyForName("MainView")).getAbsolutePath().replace("\\","/")));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HBox hBox = null;
        try {
            hBox = (HBox) fxmlLoader.load();
        } catch (IOException ex) {

            DialogMessengerElementForm.showError("Ошибка загрузки главного окна", ex.getMessage());
        }

        Scene scene = new Scene(hBox, 600, 400);
        stage.setTitle("Учет времени");
        stage.setScene(scene);

     //   stage.getIcons().add(new Image(new File(TimeTracker.getPropertyForName("Icon")).getAbsolutePath()));

//        Bean.timeTrackerController = fxmlLoader.getController();

//        Bean.timeTrackerController.setComboBoxTime(Bean.timeList);
//        Bean.timeTrackerController.setComboBoxTypeTimeDiagram();
//        Bean.timeTrackerController.setTimePie();

        stage.show();
    }
}
