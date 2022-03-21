package ru.saumlaki.time_tracker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.saumlaki.time_tracker.dao.TimeDAOImpl;
import ru.saumlaki.time_tracker.dao.interfaces.TimeDAO;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;

public class MainController {

    @FXML
    private TextField hourText;

    @FXML
    private TextField minText;

    @FXML
    private TextField secText;

    @FXML
    private ComboBox<Time> listTime;

    @FXML
    void initialize() throws InterruptedException {

        //1. Заполняем список видов времени(Time)
        fillListTypeOfTime();
    }


    @FXML
    void listTimeOnAction(ActionEvent event) {

    }


    private void fillListTypeOfTime() {

        TimeDAO dao = new TimeDAOImpl();
        listTime.getItems().addAll(dao.getAll());
    }

}
