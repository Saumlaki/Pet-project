package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.supporting.Error;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractView {

    public FXMLLoader showForm(Stage stage, String formName, String title, String css, String icon) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = null;
        try {
            String fileName  = new File(TimeTracker.getPropertyForName(formName)).getAbsolutePath().replace("\\", "/");
            fxmlLoader = new FXMLLoader(new URL("file:/" + fileName));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HBox hBox = null;
        try {
            hBox = (HBox) fxmlLoader.load();
        } catch (IOException ex) {

            Error.showError("Ошибка загрузки окна " + formName, ex.getMessage());
        }

        Scene scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());
        stage.setTitle(title);
        stage.setScene(scene);

        if(css!=null&&!css.isEmpty())
        scene.getStylesheets().add(css);
        //stage.getIcons().add(new Image(new File(TimeTracker.getPropertyForName("Icon")).getAbsolutePath()));

        stage.show();

        return fxmlLoader;
    }
}

