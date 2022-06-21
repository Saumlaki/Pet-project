package ru.saumlaki.time_tracker.controllers;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
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
    private ComboBox<TypeTimeDiagram> typeTimeDiagram;

    @FXML
    private PieChart pieChart;

    @FXML
    private Button startButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button settingButton;

    Time currentTime;
    Thread timerWatchThread;

    /**
     * БЛОК ОБЯЗАТЕЛЬНЫХ МЕТОДОВ
     */
    @FXML
    void initialize() {
        //Обновление списка видов времени при изменении видов времени
        TimeTracker.timeObsList.addListener((ListChangeListener) change -> timeListUpdate());

        //Обновление диаграммы времени при изменении данных по временным затратам
        TimeTracker.dataOfTimeObsList.addListener((ListChangeListener) change -> pieChartUpdate());

        //Заполнение списка типов диаграмм(по виду времени, по типу времени)
        typeTimeDiagram.getItems().add(TypeTimeDiagram.Time);
        typeTimeDiagram.getItems().add(TypeTimeDiagram.TypeTime);
        typeTimeDiagram.setValue(TypeTimeDiagram.Time);
    }

    /**
     * Метод устанавливает горячие клавиши формы
     */
    @Override
    public void setMnemonic() {
        //Установка всплывающих подсказок
        updateButton.setTooltip(new Tooltip("Обновляет круговую диаграмму(Alt+U)"));
        settingButton.setTooltip(new Tooltip("Открывает окно настроек(Alt+S)"));
        startButton.setTooltip(new Tooltip("Запускает/останавливает таймер(Alt+R)"));

        //Добавление быстрых кнопок
        scene.addMnemonic(new Mnemonic(updateButton, KeyCombination.keyCombination("Alt+'U'")));
        scene.addMnemonic(new Mnemonic(updateButton, KeyCombination.keyCombination("Alt+'Г'")));

        scene.addMnemonic(new Mnemonic(settingButton, KeyCombination.keyCombination("Alt+'S'")));
        scene.addMnemonic(new Mnemonic(settingButton, KeyCombination.keyCombination("Alt+'Ы'")));

        scene.addMnemonic(new Mnemonic(startButton, KeyCombination.keyCombination("Alt+'R'")));
        scene.addMnemonic(new Mnemonic(startButton, KeyCombination.keyCombination("Alt+'K'")));
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
            timeList.setValue(timeList.getItems().get(0));
    }

    @Override
    public void updateElement() {
        //Место для вашей рекламы
        //Заглушка
    }

    /**ОБРАБОТЧИКИ КНОПОК И СОБЫТИЙ ЭЛЕМЕНТОВ ФОРМЫ
     */

    /**
     * Обработчик кнопки запуска таймера
     */
    @FXML
    void startOnAction(ActionEvent event) {
        //Проверяем что тип времени выбран
        if (timeList.getValue() == null) {

            Error.showError("Не указан вид времени", "Перед запускам таймера необходимо выбрать вид времени.\n" +
                    "Если список видов времени не заполнен, то его необходимо заполнить в настройках.");
            return;
        }

        if (timerWatchThread == null || timerWatchThread.isInterrupted()) {

            element = new TimerWatch(this);
            timerWatchThread = new Thread(element);

            Platform.runLater(() -> timerWatchThread.start());

            startButton.setText("Стоп");
            timeList.setDisable(true);
        } else {
            int time = element.getValue();
            timerWatchThread.interrupt();

            DataOfTime dataOfTime = new DataOfTime(0, SimpleCalendar.getBeginningCurrentDay(), timeList.getValue(), time);
            ServiceFactory.getService(dataOfTime.getClass()).add(dataOfTime);

            //Устанавливаем текст кнопки
            startButton.setText("Старт");

            //Обнуляем поля отображения времени
            setTime(0);

            //Обновляем данные по временным затратам
            TimeTracker.dataOfTimeObsListUpdate();

            //Ставим доступность выбора типа времени
            timeList.setDisable(false);
        }
    }

    /**
     * Обработчик выпадающего списка смены типа диаграммы
     */
    @FXML
    void typeTimeDiagramOnAction(ActionEvent event) {
        pieChartUpdate();
    }

    /**
     * Обработчик кнопки обновить. Обновляет круговую диаграмму
     */
    @FXML
    void updateOnAction(ActionEvent event) {
        TimeTracker.dataOfTimeObsListUpdate();
    }

    /**
     * Обработчик кнопки "Настройки". Открывает окно настроек программы
     */
    @FXML
    void settingOnAction(ActionEvent event) {
        /*В начале перечитаем информацию по всем временным затратам для формы корректровки данных*/
        TimeTracker.dataOfTimeObsListAllUpdate();
        new Setting().showForm(null, stage);
    }

    /**ПРОЧИЕ МЕТОДЫ*/

    /**
     * Метод обновляет круговую диаграмму времени.
     */
    private void pieChartUpdate() {

        pieChart.getData().clear();

        //Заполнение диаграммы в зависимости от типа времени
        if (typeTimeDiagram.getValue() == TypeTimeDiagram.Time) {
            TimeTracker.dataOfTimeObsList.forEach(a -> pieChart.getData().add(new PieChart.Data(a.toString(), a.getValues())));
        } else {

            Map<TypeOfTime, Integer> map = new HashMap<>();

            TimeTracker.dataOfTimeObsList.forEach(a -> map.put(a.getTime().getTypeOfTime(),
                    a.getValues() + nullOrInt(map.get(a.getTime().getTypeOfTime()))));

            for (Map.Entry<TypeOfTime, Integer> entry : map.entrySet()) {
                //Для преобразования значения времени в удобочитаемый вид делаем костыль
                DataOfTime dto = new DataOfTime();
                dto.setTime(new Time(1, entry.getKey().toString(), new TypeOfTime()));
                dto.setValues(entry.getValue());

                pieChart.getData().add(new PieChart.Data(dto.toString(), dto.getValues()));
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
        int locMinutes = (seconds - locHours * 3600) / 60;
        int locSeconds = seconds - locHours * 3600 - locMinutes * 60;

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

    //---Прочее--

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
