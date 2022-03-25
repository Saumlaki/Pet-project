package ru.saumlaki.time_tracker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DataOfTimeElementController extends AbstractElementController<DataOfTime> {

    //***ПОЛЯ ФОРМЫ***

    @FXML
    private DatePicker calendar;

    @FXML
    private ComboBox<Time> time;

    @FXML
    private TextField values;

    //***БЛОК ОБЯЗАТЕЛЬНЫХ МЕТОДОВ***

    //***Инициализация формы***

    @FXML
    void initialize() {

        /**Установка прослушки для ввода только числовых значений*/
        values.textProperty().addListener((observableValue, oldValue, newValue) -> {

            if (newValue.isEmpty()) values.setText("0");
            else if (newValue.matches("[0-9]+")) values.setText(newValue);
            else values.setText(oldValue);

            if (oldValue.equals("0") && newValue.length() == 2) values.setText(newValue.substring(1, 2));
        });
    }

    //***Обновление формы и элемента***

    @Override
    public void updateForm() {
        if (element != null) {

            calendar.setValue(LocalDate.of(element.getCalendar().get(Calendar.YEAR),
                    element.getCalendar().get(Calendar.MONTH)+1,
                    element.getCalendar().get(Calendar.DAY_OF_MONTH)));

            time.getItems().clear();
            time.setItems(TimeTracker.timeObsList);
            time.setValue(element.getTime());

            values.setText(String.valueOf(element.getValues()));
        }
    }

    @Override
    public void updateElement() {
        //Заполнение полей объекта
        element.setValues(Integer.parseInt(values.getText()));
        element.setTime(time.getValue());

        Calendar calendarTemp = new GregorianCalendar(calendar.getValue().getYear(),
                calendar.getValue().getMonth().getValue() - 1,
                calendar.getValue().getDayOfMonth());

        element.setCalendar(calendarTemp);
    }

    //***ОБРАБОТЧИКИ КНОПОК***

    //---группа - Ок, Отмена---

    @FXML
    void okOnAction(ActionEvent event) {

        if (save("values, time, calendar")) {

            //Обновление подчиненных форм
            TimeTracker.dataOfTimeObsList.clear();
            TimeTracker.dataOfTimeObsList.addAll(ServiceFactory.getService(element.getClass()).getAll());
            closeForm();
        }
    }

    @FXML
    void cancelOnAction(ActionEvent event) {

        closeForm();
    }
}



