package ru.saumlaki.price_dynamic.controller.jfx.support;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Setter;

public class ErrorMessageController {

    @Setter
    Stage stage;

    @FXML
    private Label labelErrorShortMessage;

    @FXML
    private TextArea textErrorMessage;

    @FXML
    void onClickButtonCancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void pres(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED && event.getCode() == KeyCode.ESCAPE)
            stage.close();
    }
}
