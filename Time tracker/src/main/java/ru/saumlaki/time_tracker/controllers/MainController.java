package ru.saumlaki.time_tracker.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.DataOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.TimeServiceImpl;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;
import ru.saumlaki.time_tracker.supporting.TimerWatch;
import ru.saumlaki.time_tracker.supporting.TypeTimeDiagram;
import ru.saumlaki.time_tracker.supporting.data.SimpleCalendar;
import ru.saumlaki.time_tracker.view.Setting;

import java.util.HashMap;
import java.util.Map;

public class MainController extends AbstractElementController<TimerWatch> {

    //***ПОЛЯ ФОРМЫ***

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

    @FXML
    private ComboBox<TypeTimeDiagram> typeTimeDiagram;

    @FXML
    private Button startButton;

    //***БЛОК ОБЯЗАТЕЛЬНЫХ МЕТОДОВ***

    //***Инициализация формы***

    @FXML
    void initialize() {

        //Инициализация списка типов времени
        TimeTracker.timeObsList.addListener((ListChangeListener) change -> {
            timeListUpdate();
        });

        //Инициализация списка временных затрат
        TimeTracker.dataOfTimeObsList.addListener((ListChangeListener) change -> {
            pieChartUpdate();
        });

        //Заполнение списка типов диаграмм
        typeTimeDiagram.getItems().add(TypeTimeDiagram.Time);
        typeTimeDiagram.getItems().add(TypeTimeDiagram.TypeTime);
        typeTimeDiagram.setValue(TypeTimeDiagram.Time);
    }

    //***Обновление формы и элемента***

    @Override
    public void updateForm() {

        //1. Заполняем круговую диаграмму
        fillListDataOfTimesObs();

        //2. Заполняем список видов времени(Time)
        fillTimeObsList();

        //Устанавливаем начальные значения
        setInitialValue();
    }

    @Override
    public void updateElement() {
        //Место для вашей рекламы
        //Заглушка
    }

    //***ОБРАБОТЧИКИ КНОПОК И СОБЫТИЙ ЭЛЕМЕНТОВ ФОРМЫ***

    /**
     * Обработчик кнопки запуска таймера
     */
    @FXML
    void startOnaAction(ActionEvent event) {

        if (element.isRun()) {

            //Останавливаем таймер и сохраняем значение
            DataOfTime dataOfTime = new DataOfTime(0, SimpleCalendar.getBeginningCurrentDay(), timeList.getValue(), element.stopTimer());
            ServiceFactory.getService(dataOfTime.getClass()).add(dataOfTime);

            //Устанавливаем текст кнопки
            startButton.setText("Старт");

            //Обнуляем поля отображения времени
            setTime(0);

            //Обновляем диаграмму
            fillListDataOfTimesObs();

        } else {
            element.startTimer();
            startButton.setText("Стоп");
        }
    }

    //***Обработчики событий формы***

    @FXML
    void listTimeOnAction(ActionEvent event) {

    }

    @FXML
    void typeTimeDiagramOnAction(ActionEvent event) {
        pieChartUpdate();
    }

    /**
     * Обработчик кнопки обновить. Обновляет круговую диаграмму
     */
    @FXML
    void updateOnAction(ActionEvent event) {

        pieChartUpdate();
    }

    /**
     * Обработчик кнопки "Настройки". Открывает окно настроек программы
     */
    @FXML
    void settingOnAction(ActionEvent event) {

        new Setting().showForm(null);
    }

    //***ПРОЧИЕ МЕТОДЫ***

    //---Заполнение Obs списков---

    private void fillTimeObsList() {

        TimeTracker.timeObsList.clear();
        TimeTracker.timeObsList.addAll(ServiceFactory.getService(Time.class).getAll());
    }

    private void fillListDataOfTimesObs() {

        TimeTracker.dataOfTimeObsList.clear();
        TimeTracker.dataOfTimeObsList.addAll(ServiceFactory.getService(DataOfTime.class).getAll());
    }

    //---Установка начальных значений---

    private void setInitialValue() {

        if (timeList.getItems().size() > 0)
            timeList.setValue(timeList.getItems().get(0));
    }

    //---Обновление данных элементов формы---

    private void pieChartUpdate() {

        pieChart.getData().clear();

        //Заполнение диаграммы в зависимости от типа времени
        if (typeTimeDiagram.getValue() == TypeTimeDiagram.Time) {
            TimeTracker.dataOfTimeObsList.forEach(a -> pieChart.getData().add(new PieChart.Data(a.getValueToStr(), a.getValues())));
        } else {

            Map<TypeOfTime, Integer> map = new HashMap<>();

            TimeTracker.dataOfTimeObsList.forEach(a -> map.put(a.getTime().getTypeOfTime(),
                    a.getValues() + nullOrInt(map.get(a.getTime().getTypeOfTime()))));

            for (Map.Entry<TypeOfTime, Integer> entry : map.entrySet()) {
                pieChart.getData().add(new PieChart.Data(entry.getKey().toString() + "(" + entry.getValue() + ")", entry.getValue()));
            }

        }

    }

    private void timeListUpdate() {

        //Получаем текущее значение
        Time currentValue = timeList.getValue();

        timeList.getItems().clear();
        timeList.getItems().addAll(TimeTracker.timeObsList);

        if (currentValue != null&&TimeTracker.timeObsList.contains(currentValue)) {
            timeList.setValue(currentValue);
        }
    }

    //---Установка отображения времени---

    public void setTime(int seconds) {

        int locHours = seconds / 3600;
        int locMinutes = (seconds - locHours * 60) / 60;
        int locSeconds = seconds - locHours * 60 - locMinutes * 60;

        setHourText(locHours);
        setMinText(locMinutes);
        setSecText(locSeconds);
    }

    private void setHourText(int hours) {

        hourText.setText(getFormattedTime(hours));
    }

    private void setMinText(int minutes) {

        minText.setText(getFormattedTime(minutes));
    }

    private void setSecText(int seconds) {

        secText.setText(getFormattedTime(seconds));
    }

    private String getFormattedTime(int time) {

        String formattedTime = String.valueOf(time);
        while (formattedTime.length() < 2) {
            formattedTime = "0" + formattedTime;
        }

        return formattedTime;
    }

    private int nullOrInt(Integer value) {

        if (value == null) return 0;
        else return value;
    }
}
