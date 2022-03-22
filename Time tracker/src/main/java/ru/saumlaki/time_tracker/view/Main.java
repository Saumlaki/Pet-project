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

public class Main extends AbstractView{

    public void showForm(Stage stage) {

        super.showForm(stage, "MainView", "Учет времени", "chart.css", "Icon");
    }
}
