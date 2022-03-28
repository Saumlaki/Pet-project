package ru.saumlaki.time_tracker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.*;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;

public class TypeOfTimeController extends AbstractElementController<TypeOfTime> {

    //***ПОЛЯ ФОРМЫ***

    @FXML
    private TextField description;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    //***БЛОК ОБЯЗАТЕЛЬНЫХ МЕТОДОВ***

    //***Инициализация формы***

    @FXML
    void initialize() {

    }

    /**
     * Метод устанавлиает горячие клавиши формы
     */
    @Override
    public void setMnemonic() {

        //Установка всплывающих подсказок
        okButton.setTooltip(new Tooltip("Сохраняет элемент в базу(CRT+S)"));
        cancelButton.setTooltip(new Tooltip("Закрыть без сохранения(Esc)"));

        //Добавление быстрых кнопок
//        scene.addMnemonic(new Mnemonic(okButton, new KeyCodeCombination(KeyCode.S, KeyCode.CONTROL)));
        scene.addMnemonic(new Mnemonic(okButton, KeyCombination.keyCombination("Alt+'Ы'")));
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
            closeForm();
        }
    }

    @FXML
    void cancelOnAction(ActionEvent event) {

        closeForm();
    }

}
