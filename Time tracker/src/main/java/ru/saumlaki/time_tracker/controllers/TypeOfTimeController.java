package ru.saumlaki.time_tracker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;

public class TypeOfTimeController extends AbstractElementController<TypeOfTime> {

    //***ПОЛЯ ФОРМЫ***

    @FXML
    private TextField description;

    //***БЛОК ОБЯЗАТЕЛЬНЫХ МЕТОДОВ***

    //***Инициализация формы***

    @FXML
    void initialize() {

        //Место для вашей реклама!
    }

    //***Обновление формы и элемента***

    @Override
    public void updateForm() {
        if (element != null) {
            description.setText(element.getDescription());
        }
    }

    @Override
    public void updateElement() {
        //Заполнение полей объекта
        element.setDescription(description.getText());
    }

    //***ОБРАБОТЧИКИ КНОПОК***

    //---группа - Ок, Отмена---
    @FXML
    void okOnAction(ActionEvent event) {

        if (save("description")){

            //Обновление подчиненных форм
            TimeTracker.typeOfTimeObsList.clear();
            TimeTracker.typeOfTimeObsList.addAll(ServiceFactory.getService(element.getClass()).getAll());
            stage.close();
        }
    }

    @FXML
    void cancelOnAction(ActionEvent event) {

        closeForm();
    }
}
