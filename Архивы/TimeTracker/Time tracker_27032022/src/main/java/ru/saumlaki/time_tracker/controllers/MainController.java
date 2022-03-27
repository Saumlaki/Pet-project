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
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;
import ru.saumlaki.time_tracker.supporting.Error;
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

    Time currentTime;

    //***БЛОК ОБЯЗАТЕЛЬНЫХ МЕТОДОВ***

    //***Инициализация формы***

    @FXML
    void initialize() {

        //Инициализация списка типов времени
        TimeTracker.timeObsList.addListener((ListChangeListener) change -> timeListUpdate());

        //Инициализация списка временных затрат
        TimeTracker.dataOfTimeObsList.addListener((ListChangeListener) change -> pieChartUpdate());

        //Заполнение списка типов диаграмм
        typeTimeDiagram.getItems().add(TypeTimeDiagram.Time);
        typeTimeDiagram.getItems().add(TypeTimeDiagram.TypeTime);
        typeTimeDiagram.setValue(TypeTimeDiagram.Time);
    }

    //***Обновление формы и элемента***

    @Override
    public void updateForm() {

        //1. Обновляем данные по временным затратам
        TimeTracker.dataOfTimeObsListUpdate();

        //2. Заполняем список видов времени(Time)
        TimeTracker.timeObsListUpdate();

        //Устанавливаем начальные значения для списка выбора вида времени
        if (timeList.getItems().size() > 0)
            timeList.setValue(timeList.getItems().get(0));;
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
    void startOnAction(ActionEvent event) {

        //Проверяем что тип времени выбран
        if (timeList.getValue() == null) {

            Error.showError("Не указан вид времени", "Перед запускам таймеа необходимо выбрать вид времени.\n" +
                    "Если список видов времени не заполнен, то его необходимо заполнить в настройках.");
            return;
        }

        if (element.isRun()) {

            //Останавливаем таймер и сохраняем значение
            DataOfTime dataOfTime = new DataOfTime(0, SimpleCalendar.getBeginningCurrentDay(), timeList.getValue(), element.stopTimer());
            ServiceFactory.getService(dataOfTime.getClass()).add(dataOfTime);

            //Устанавливаем текст кнопки
            startButton.setText("Старт");

            //Обнуляем поля отображения времени
            setTime(0);

            //Обновляем данные по временным затратам
            TimeTracker.dataOfTimeObsListUpdate();

            //Ставим доступность выбора типа времени
            timeList.setDisable(false);
        } else {
            element.startTimer();
            startButton.setText("Стоп");

            //Убираем доступность выбора типа времени
            timeList.setDisable(true);
        }
    }

    //***Обработчики событий формы***

    @FXML
    void typeTimeDiagramOnAction(ActionEvent event) {

        pieChartUpdate();
    }

    /**
     * Обработчик кнопки обновить. Обновляет круговую диаграмму
     */
    @FXML
    void updateOnAction(ActionEvent event) {

        //Обновляем данные по временным затратам
        TimeTracker.dataOfTimeObsListUpdate();
    }

    /**
     * Обработчик кнопки "Настройки". Открывает окно настроек программы
     */
    @FXML
    void settingOnAction(ActionEvent event) {

        new Setting().showForm(null);
    }

    //***ПРОЧИЕ МЕТОДЫ***

    //---Установка начальных значений---

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

    /**
     * Метод обновляет поле выбора <code>timeList</code> актуальными данными
     */
    private void timeListUpdate() {

        //Получаем текущее/прошлое значение
        currentTime = timeList.getValue() == null ? currentTime : timeList.getValue();

        //Обновляем список
        timeList.getItems().clear();
        timeList.getItems().addAll(TimeTracker.timeObsList);

        //Устанавливаем текущее значение списка
        if (timeList.getItems().contains(currentTime)) {
            timeList.setValue(currentTime);
        } else if (timeList.getItems().size() > 0) {
            timeList.setValue(timeList.getItems().get(0));
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
