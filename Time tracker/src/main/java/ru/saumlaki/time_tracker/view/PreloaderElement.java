package ru.saumlaki.time_tracker.view;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.supporting.Error;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**Класс заставка.ЗАчем? Просто что бы был*/
public class PreloaderElement extends Preloader {

    private Stage window;
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = null;
        try {
            String fileName  = new File(TimeTracker.getPropertyForName("PreloaderView")).getAbsolutePath().replace("\\", "/");
            fxmlLoader = new FXMLLoader(new URL("file:/" + fileName));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HBox hBox = null;
        try {
            hBox = (HBox) fxmlLoader.load();
        } catch (IOException ex) {

            Error.showError("Ошибка загрузки окна PreloaderView" , ex.getMessage());
        }

        Scene scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());
        stage.setScene(scene);

        stage.initStyle(StageStyle.UNDECORATED);
        this.window = stage;
        stage.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification preloaderNotification) {
        this.window.close();
    }
}
