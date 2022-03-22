package ru.saumlaki.time_tracker.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.DialogMessengerElementForm;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.TypeOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.interfaces.TypeOfTimeService;
import ru.saumlaki.time_tracker.view.Main;

public class TypeOfTimeController {

    //***Элемент данных"***
    TypeOfTime element;
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
            TypeOfTimeService typeOfTimeService = new TypeOfTimeServiceImpl();

            typeOfTimeService.add(element);

            TimeTracker.typeOfTimeObsList.clear();
            TimeTracker.typeOfTimeObsList.addAll(typeOfTimeService.getAll());
            stage.close();
        }
    }

    @FXML
    void cancelOnAction(ActionEvent event) {

        stage.close();
    }

    //***Прочее***

    public void setElement(TypeOfTime element) {

        this.element = element;
        initialize();
    }

    public void setStage(Stage stage) {

        this.stage = stage;
    }
}
