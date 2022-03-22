package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class Setting extends  AbstractView{

    public void showForm(Stage stage) {

        super.showForm(stage, "SettingView", "Настройки", null, null);
    }
}
