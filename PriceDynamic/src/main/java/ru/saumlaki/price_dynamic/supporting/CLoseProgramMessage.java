package ru.saumlaki.price_dynamic.supporting;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Класс реализует диалоговое окно подтверждения выхода из программы
 */
public class CLoseProgramMessage {
    public static boolean show() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
        alert.setTitle("Выйти из программы?");
        alert.setHeaderText("Вы действительно хотите выйти из программы?");

        return alert.showAndWait().get().equals(ButtonType.OK);
    }
}
