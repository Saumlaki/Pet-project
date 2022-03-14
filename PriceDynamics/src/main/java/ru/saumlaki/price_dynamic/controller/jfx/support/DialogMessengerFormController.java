package ru.saumlaki.price_dynamic.controller.jfx.support;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DialogMessengerFormController  {

    Stage stage;

    @FXML
    private Button buttonOK;

    @FXML
    private Label labelErrorShortMessage;

    @FXML
    private TextArea textErrorMessage;

    @FXML
    void onClickButtonCancel(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {

        this.stage = stage;
    }

    public Stage getStage() {

        return stage;
    }

    public void setLabelErrorShortMessage(String text) {

        labelErrorShortMessage.setText(text);
    }

    public void setTextErrorMessage(String text) {

        textErrorMessage.setText(text);
    }

    // Метод инициализации
//    @Override
//    public void init(DialogMessengerElementForm object, Stage stage) {
//        this.object = object;
//        this.stage = stage;
//
//        labelErrorShortMessage.setText(object.getText());
//        textErrorMessage.setText(object.getErrorMessage());
//    }


    @FXML
    void pres(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED && event.getCode() == KeyCode.ESCAPE)
            stage.close();
    }

    @FXML
    void relas(KeyEvent event) {

    }
}
