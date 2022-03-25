package ru.saumlaki.time_tracker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;

public class TimeController extends AbstractElementController<Time> {

    //***ПОЛЯ ФОРМЫ***

    @FXML
    private TextField description;

    @FXML
    private ComboBox<TypeOfTime> typeOfTime;

    //***БЛОК ОБЯЗАТЕЛЬНЫХ МЕТОДОВ***

    //***Инициализация формы***

    @FXML
    void initialize() {

        typeOfTime.setItems(TimeTracker.typeOfTimeObsList);
    }

    //***Обновление формы и элемента***

    @Override
    public void updateForm() {
        if (element != null) {
            description.setText(element.getDescription());
            typeOfTime.setValue(element.getTypeOfTime());
        }
    }

    @Override
    public void updateElement() {
        //Заполнение полей объекта
        element.setDescription(description.getText());
        element.setTypeOfTime(typeOfTime.getValue());
    }

    //***ОБРАБОТЧИКИ КНОПОК***

    //---Группа - Ок, Отмена---

    @FXML
    void okOnAction(ActionEvent event) {

        if (save("description, typeOfTime")) {

            //Обновление подчиненных форм
            TimeTracker.timeObsList.clear();
            TimeTracker.timeObsList.addAll(ServiceFactory.getService(element.getClass()).getAll());
            stage.close();
        }
    }

    @FXML
    void cancelOnAction(ActionEvent event) {

        closeForm();
    }
}
