package ru.saumlaki.time_tracker.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.DataOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;
import ru.saumlaki.time_tracker.view.DataOfTimeElement;
import ru.saumlaki.time_tracker.view.TimeElement;
import ru.saumlaki.time_tracker.view.TypeOfTimeElement;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class SettingController extends AbstractElementController<Object> {

    //***ПОЛЯ ФОРМЫ***

    @FXML
    private TableView<TypeOfTime> typeOfTimeTable;

    @FXML
    private TableView<Time> timeTable;

    @FXML
    private TableView<DataOfTime> dataOfTimeTable;

    @FXML
    private LineChart<String, Number> dataOfTimeLineChart;

    @FXML
    private DatePicker periodBegin;

    @FXML
    private DatePicker periodEnd;


    //***ИНИЦИАЛИЗАЦИЯ ФОРМЫ***

    @FXML
    void initialize() throws InterruptedException {

        //Создаем колонки таблиц
        creteColumn();

        //Создаем контекстные меню
        createContextMenu();

        //Подключаем слушателей к спискам
        TimeTracker.typeOfTimeObsList.addListener((ListChangeListener) change -> typeOfTimeTable.setItems(TimeTracker.typeOfTimeObsList));
        TimeTracker.timeObsList.addListener((ListChangeListener) change -> timeTable.setItems(TimeTracker.timeObsList));
        TimeTracker.dataOfTimeObsListALL.addListener((ListChangeListener) change -> dataOfTimeTable.setItems(TimeTracker.dataOfTimeObsListALL));

        //Обновляем данные на форме
        typeOfTimeTable.setItems(TimeTracker.typeOfTimeObsList);
        timeTable.setItems(TimeTracker.timeObsList);
        dataOfTimeTable.setItems(TimeTracker.dataOfTimeObsListALL);

        //Обновление данных диагарммы
        periodEnd.setValue(LocalDate.now());
        periodBegin.setValue(LocalDate.of(periodEnd.getValue().getYear(),
                periodEnd.getValue().getMonth(),
                1));

        dataOfTimeLineChartUpdate();
    }

    //***ОБРАБОТЧИКИ СОБЫТИЙ ФОРМЫ***

    //---Обработчики событий таблицы time---

    @FXML
    void timeAdd(ActionEvent event) {

        new TimeElement().showForm(null, stage, new Time());
    }

    @FXML
    void timeChange(ActionEvent event) {

        new TimeElement().showForm(null, stage, timeTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void timeRemove(ActionEvent event) {

        ServiceFactory.getService(Time.class).remove(timeTable.getSelectionModel().getSelectedItem());

        TimeTracker.timeObsListUpdate();
        TimeTracker.dataOfTimeObsListUpdate();
    }

    //---Обработчики событий таблицы typeOfTime---

    @FXML
    void typeOfTimeAdd(ActionEvent event) {

        new TypeOfTimeElement().showForm(null, stage, new TypeOfTime());
    }

    @FXML
    void typeOfTimeChange(ActionEvent event) {

        new TypeOfTimeElement().showForm(null, stage, typeOfTimeTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void typeOfTimeRemove(ActionEvent event) {

        ServiceFactory.getService(TypeOfTime.class).remove(typeOfTimeTable.getSelectionModel().getSelectedItem());

        TimeTracker.typeOfTimeObsListUpdate();
        TimeTracker.timeObsListUpdate();
        TimeTracker.dataOfTimeObsListUpdate();
    }

    //---Обработчики событий таблицы dataOfTime---

    @FXML
    void dataOfTimeAdd(ActionEvent event) {

        new DataOfTimeElement().showForm(null, stage, new DataOfTime());

        TimeTracker.dataOfTimeObsListUpdate();
        TimeTracker.dataOfTimeObsListAllUpdate();
    }

    @FXML
    void dataOfTimeChange(ActionEvent event) {

        new DataOfTimeElement().showForm(null, stage, dataOfTimeTable.getSelectionModel().getSelectedItem());

        TimeTracker.dataOfTimeObsListUpdate();
        TimeTracker.dataOfTimeObsListAllUpdate();
    }

    @FXML
    void dataOfTimeRemove(ActionEvent event) {

        ServiceFactory.getService(DataOfTime.class).remove(dataOfTimeTable.getSelectionModel().getSelectedItem());

        TimeTracker.dataOfTimeObsListUpdate();
        TimeTracker.dataOfTimeObsListAllUpdate();
    }

    //---Обработчики выбора периода отображения диаграммы---

    @FXML
    void periodBeginOnAction(ActionEvent event) {

        dataOfTimeLineChartUpdate();
    }

    @FXML
    void periodEndOnAction(ActionEvent event) {

        dataOfTimeLineChartUpdate();
    }

    //***ПРОЧИЕ МЕТОДЫ***

    //---Создание колонок таблиц формы---

    private void creteColumn() {

        createColumnTime();
        createColumnTypeOfTime();
        createColumnDataOfTime();
    }

    private void createContextMenu() {

        //Типы времени
        MenuItem menuItemTypeOfTimeAdd = new MenuItem("Добавить");
        MenuItem menuItemTypeOfTimeUpdate = new MenuItem("Изменить");
        MenuItem menuItemTypeOfTimeRemove = new MenuItem("Удалить");

        menuItemTypeOfTimeAdd.setOnAction(e -> typeOfTimeAdd(null));
        menuItemTypeOfTimeUpdate.setOnAction(e -> typeOfTimeChange(null));
        menuItemTypeOfTimeRemove.setOnAction(e -> typeOfTimeRemove(null));

        typeOfTimeTable.setContextMenu(new ContextMenu(menuItemTypeOfTimeAdd, menuItemTypeOfTimeUpdate, menuItemTypeOfTimeRemove));

        typeOfTimeTable.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2)
                typeOfTimeChange(null);
        });

        //Виды времени
        MenuItem menuItemTimeAdd = new MenuItem("Добавить");
        MenuItem menuItemTimeUpdate = new MenuItem("Изменить");
        MenuItem menuItemTimeRemove = new MenuItem("Удалить");

        menuItemTimeAdd.setOnAction(e -> timeAdd(null));
        menuItemTimeUpdate.setOnAction(e -> timeChange(null));
        menuItemTimeRemove.setOnAction(e -> timeRemove(null));

        timeTable.setContextMenu(new ContextMenu(menuItemTimeAdd, menuItemTimeUpdate, menuItemTimeRemove));

        timeTable.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2)
                timeChange(null);
        });


        //Данные по временным затратам времени
        MenuItem menuItemDataOfTimeAdd = new MenuItem("Добавить");
        MenuItem menuItemDataOfTimeUpdate = new MenuItem("Изменить");
        MenuItem menuItemDataOfTimeRemove = new MenuItem("Удалить");

        menuItemDataOfTimeAdd.setOnAction(e -> dataOfTimeAdd(null));
        menuItemDataOfTimeUpdate.setOnAction(e -> dataOfTimeChange(null));
        menuItemDataOfTimeRemove.setOnAction(e -> dataOfTimeRemove(null));

        dataOfTimeTable.setContextMenu(new ContextMenu(menuItemDataOfTimeAdd, menuItemDataOfTimeUpdate, menuItemDataOfTimeRemove));

        dataOfTimeTable.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2)
                dataOfTimeChange(null);
        });

    }

    private void createColumnTime() {
        TableColumn<TypeOfTime, String> descriptionColumn = new TableColumn<>("Наименование");
        descriptionColumn.setCellValueFactory(StringCellDataFeatures -> new SimpleStringProperty(StringCellDataFeatures.getValue().getDescription()));

        descriptionColumn.setPrefWidth(100);

        typeOfTimeTable.getColumns().add(descriptionColumn);
    }

    private void createColumnTypeOfTime() {

        TableColumn<Time, String> descriptionColumn = new TableColumn<>("Наименование");
        descriptionColumn.setCellValueFactory(StringCellDataFeatures -> new SimpleStringProperty(StringCellDataFeatures.getValue().getDescription()));

        TableColumn<Time, String> typeOfTimeColumn = new TableColumn<>("Тип времени");
        typeOfTimeColumn.setCellValueFactory(StringCellDataFeatures -> new SimpleStringProperty(StringCellDataFeatures.getValue().getTypeOfTime().getDescription()));

        descriptionColumn.setPrefWidth(100);
        typeOfTimeColumn.setPrefWidth(100);

        timeTable.getColumns().addAll(descriptionColumn, typeOfTimeColumn);
    }

    private void createColumnDataOfTime() {

        TableColumn<DataOfTime, String> dataColumn = new TableColumn<>("Дата");
        dataColumn.setCellValueFactory(StringCellDataFeatures -> new SimpleStringProperty(StringCellDataFeatures.getValue().getDateToStr()));

        TableColumn<DataOfTime, String> timeColumn = new TableColumn<>("Вид времени");
        timeColumn.setCellValueFactory(StringCellDataFeatures -> new SimpleStringProperty(StringCellDataFeatures.getValue().getTime().toString()));

        TableColumn<DataOfTime, String> valueColumn = new TableColumn<>("Время");
        valueColumn.setCellValueFactory(StringCellDataFeatures -> new SimpleStringProperty(String.valueOf(StringCellDataFeatures.getValue().getValueToStr())));

        dataColumn.setPrefWidth(100);
        timeColumn.setPrefWidth(100);
        valueColumn.setPrefWidth(100);

        dataOfTimeTable.getColumns().addAll(dataColumn, timeColumn, valueColumn);
    }

    //---ОБновление элементов формы---

    private void dataOfTimeLineChartUpdate() {

        dataOfTimeLineChart.getData().clear();
        dataOfTimeLineChart.setTitle("Динамика временных затрат за период с " + periodBegin.getValue() + " по " + periodEnd.getValue());
        dataOfTimeLineChart.setCreateSymbols(false);

        //Строим временную шкалу по дням по заданному периоду
        ObservableList<String> dateList = FXCollections.observableArrayList();
        LocalDate tempDate = periodBegin.getValue();
        while (tempDate.isBefore(periodEnd.getValue())) {
            dateList.add(tempDate.toString());
            tempDate = tempDate.plusDays(1);
        }

        dateList.add(periodEnd.getValue().toString());

        //Формируем шкалу X
        CategoryAxis xAxis = new CategoryAxis(dateList);
        xAxis.setRotate(-90.0);

        //Формируем шкалу Y
        NumberAxis yAxis = new NumberAxis();

        //Формируем серии
        Instant instBegin = periodBegin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant instEnd = periodEnd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant();
        List<DataOfTime> dataOfTimeList = new DataOfTimeServiceImpl().getByPeriod(Date.from(instBegin), Date.from(instEnd));

        //Получаем множество типов времени
        Set<Time> timeSet = new HashSet<>();
        dataOfTimeList.stream().forEach(a -> timeSet.add(a.getTime()));

        //Для каждого типа времени получаем его даты и временные ограничения
        for (Time time : timeSet) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(time.toString());

            //Пройдемся по датам что бы на имнтервале заполнить пустые значения
            tempDate = periodBegin.getValue();
            while (tempDate.isBefore(periodEnd.getValue())) {


                Calendar calendarTemp = new GregorianCalendar(tempDate.getYear(),
                        tempDate.getMonth().getValue() - 1,
                        tempDate.getDayOfMonth());

                List<DataOfTime> tempList = dataOfTimeList.stream().filter(a -> a.getTime().equals(time) && a.getCalendar().equals(calendarTemp)).collect(Collectors.toList());
                if (tempList.size() > 0)
                    series.getData().add(new XYChart.Data<String, Number>(tempDate.toString(), tempList.get(0).getValues() / 60));
                else
                    series.getData().add(new XYChart.Data<String, Number>(tempDate.toString(), 0));

                tempDate = tempDate.plusDays(1);

            }

            dataOfTimeLineChart.getData().add(series);
        }
    }

    @Override
    public void updateForm() {

    }

    @Override
    public void updateElement() {

    }

    @Override
    public void setMnemonic() {

    }
}
