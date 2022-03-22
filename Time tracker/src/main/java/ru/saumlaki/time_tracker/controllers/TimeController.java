package ru.saumlaki.time_tracker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.DialogMessengerElementForm;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.TimeServiceImpl;
import ru.saumlaki.time_tracker.service.TypeOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.interfaces.TimeService;
import ru.saumlaki.time_tracker.service.interfaces.TypeOfTimeService;

public class TimeController {

    //***Элемент данных"***
    Time element;
    Stage stage;

    //***Поля формы***
    @FXML
    private TextField description;


    //***Инициализация формы***

    @FXML
    void initialize() {

        if (element != null)
            description.setText(element.getDescription());
    }

    //***Обработчики событий формы***

    @FXML
    void descriptionOnKeyReleased(KeyEvent event) {

        element.setDescription(description.getText());
    }

    @FXML
    void okOnAction(ActionEvent event) {

        //Проверка корректности заполнения
        if(element.getDescription().isEmpty())
            DialogMessengerElementForm.showError("Ошибка заполнения", "Наименование не может быть пустым");
        else {
            TimeService timeService = new TimeServiceImpl();

            timeService.add(element);

            TimeTracker.timeObsList.clear();
            TimeTracker.timeObsList.addAll(timeService.getAll());
            stage.close();
        }
    }

    @FXML
    void cancelOnAction(ActionEvent event) {

        stage.close();
    }

    //***Прочее***

    public void setElement(Time element) {

        this.element = element;
        initialize();
    }

    public void setStage(Stage stage) {

        this.stage = stage;
    }
}
