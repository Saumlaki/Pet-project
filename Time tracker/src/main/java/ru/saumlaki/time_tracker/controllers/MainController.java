package ru.saumlaki.time_tracker.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.service.DataOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.TimeServiceImpl;
import ru.saumlaki.time_tracker.view.Setting;

public class MainController {

    //***Поля формы***

    @FXML
    private TextField hourText;

    @FXML
    private TextField minText;

    @FXML
    private TextField secText;

    @FXML
    private ComboBox<Time> timeList;

    @FXML
    private PieChart pieChart;

    //***Инициализация формы***

    @FXML
    void initialize() {

        //Инициализация списка типов времени
        TimeTracker.timeObsList.addListener((ListChangeListener) change -> {
            timeList.getItems().clear();
            timeList.getItems().addAll(TimeTracker.timeObsList);
        });

        //Инициализация списка временных затрат
        TimeTracker.dataOfTimeObsList.addListener((ListChangeListener) change -> {
            pieChart.getData().clear();
            TimeTracker.dataOfTimeObsList.stream().forEach(a -> pieChart.getData().add(new PieChart.Data(a.getTime().toString(), a.getValues())));
        });

        //1. Заполняем список видов времени(Time)
        fillListTimeObs();

        //2. Заполняем круговую диаграмму
        fillListDataOfTimesObs();

        //Устанавливаем начальные значения
        setInitialValue();
    }

    //***Обработчики событий формы***

    @FXML
    void listTimeOnAction(ActionEvent event) {

    }

    /**
     * Обработчик кнопки обновить. Обновляет круговую диаграмму
     */
    @FXML
    void updateOnAction(ActionEvent event) {

        pieChart.getData().clear();
        TimeTracker.dataOfTimeObsList.forEach(a -> pieChart.getData().add(new PieChart.Data(a.getTime().toString(), a.getValues())));
    }

    /**
     * Обработчик кнопки "Настройки". Открывает окно настроек программы
     */
    @FXML
    void settingOnAction(ActionEvent event) {

        new Setting().showForm(null);
    }

    //***Заполнение списков***

    private void fillListTimeObs() {

        TimeTracker.timeObsList.addAll(new TimeServiceImpl().getAll());
    }

    private void fillListDataOfTimesObs() {

        TimeTracker.dataOfTimeObsList.addAll(new DataOfTimeServiceImpl().getAll());
    }

    private void setInitialValue() {

        if(timeList.getItems().size()>0)
        timeList.setValue(timeList.getItems().get(0));
    }
}
