package ru.saumlaki.time_tracker.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.saumlaki.time_tracker.entity.TypeOfTime;

public class TypeOfTimeController {

    TypeOfTime element;


    @FXML
    private TextField description;


    public void setElement(TypeOfTime element) {

        this.element = element;
        description.setText(element.getDescription());
    }
}
