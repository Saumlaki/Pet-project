package ru.saumlaki.price_dynamic.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertMessage {

    public static void showError(String error, String errorComplete ) {

        Alert alert = new Alert(Alert.AlertType.ERROR, errorComplete, ButtonType.OK);
        alert.setTitle("Ошибка");
        alert.setHeaderText(error);
        alert.show();
    }
}
