package ru.saumlaki.time_tracker.supporting;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Класс диалогового окна ошибки. При вызове показывает диалоговое окно с ошибкой и ее описанием
 */
public class Error {
    public static void showError(String errorShortMessage, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK);
        alert.setTitle("Ошибка");
        alert.setHeaderText(errorShortMessage);
        alert.show();
    }
}
