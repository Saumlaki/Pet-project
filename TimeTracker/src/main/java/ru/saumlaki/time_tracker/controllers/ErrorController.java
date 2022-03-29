package ru.saumlaki.time_tracker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ru.saumlaki.time_tracker.supporting.Error;

public class ErrorController extends AbstractElementController<Error>{

    @FXML
    private TextArea errorMessage;

    @FXML
    private Label errorShortMessage;

    @FXML
    void cancelOnAction(ActionEvent event) {
        closeForm();
    }

    @Override
    public void updateForm() {

        errorMessage.setText(element.getErrorMessage());
        errorShortMessage.setText(element.getErrorShortMessage());
    }

    @Override
    public void updateElement() {

        //Место для вашей рекламы!
        //Заглушка для реализации абстрактного метода
    }

    @Override
    public void setMnemonic() {

    }
}
