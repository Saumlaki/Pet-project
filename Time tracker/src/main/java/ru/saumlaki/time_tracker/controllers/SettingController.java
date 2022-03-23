package ru.saumlaki.time_tracker.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.DataOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.TimeServiceImpl;
import ru.saumlaki.time_tracker.service.TypeOfTimeServiceImpl;
import ru.saumlaki.time_tracker.view.Setting;
import ru.saumlaki.time_tracker.view.TimeElement;
import ru.saumlaki.time_tracker.view.TypeOfTimeElement;

public class SettingController {

    //***Поля формы***

    @FXML
    private TableView<TypeOfTime> typeOfTimeTable;

    @FXML
    private TableView<Time> timeTable;

    @FXML
    private TableView<DataOfTime> dataOfTimeTable;



    //***Инициализация формы***

    @FXML
    void initialize() throws InterruptedException {

        //Создаем колонки таблиц
        creteColumn();

        //Подключаем слушателей к спискам
        TimeTracker.typeOfTimeObsList.addListener(new TypeOfTimeListener());
        TimeTracker.timeObsList.addListener(new TimeListener());
        TimeTracker.dataOfTimeObsList.addListener(new DataOfTimeListener());

        //Обновляем данные на форме
        TimeTracker.typeOfTimeObsList.clear();
        TimeTracker.typeOfTimeObsList.addAll(new TypeOfTimeServiceImpl().getAll());

        TimeTracker.timeObsList.clear();
        TimeTracker.timeObsList.addAll(new TimeServiceImpl().getAll());

        TimeTracker.dataOfTimeObsList.clear();
        TimeTracker.dataOfTimeObsList.addAll(new DataOfTimeServiceImpl().getAll());
    }

    //***Обработчики событий формы***

    //---Time---

    @FXML
    void timeAdd(ActionEvent event) {
        new TimeElement().showForm(null, new Time());
    }

    @FXML
    void timeChange(ActionEvent event) {
        new TimeElement().showForm(null, timeTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void timeRemove(ActionEvent event) {

    }

    //---TypeOfTime---

    @FXML
    void typeOfTimeAdd(ActionEvent event) {

        new TypeOfTimeElement().showForm(null, new TypeOfTime());
    }

    @FXML
    void typeOfTimeChange(ActionEvent event) {

        new TypeOfTimeElement().showForm(null, typeOfTimeTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void typeOfTimeRemove(ActionEvent event) {

    }

    //---DataOfTime---

    @FXML
    void dataOfTimeAdd(ActionEvent event) {

    }
    @FXML
    void dataOfTimeChange(ActionEvent event) {

    }

    @FXML
    void dataOfTimeRemove(ActionEvent event) {

    }

    //***Создание таблиц***

    private void creteColumn() {

        createColumnTime();
        createColumnTypeOfTime();
        createColumnDataOfTime();
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

    //***Классы слушатели для таблиц***

    class TimeListener implements ListChangeListener<Time> {

        @Override
        public void onChanged(Change<? extends Time> change) {
            timeTable.setItems(TimeTracker.timeObsList);
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    class TypeOfTimeListener implements ListChangeListener<TypeOfTime> {

        @Override
        public void onChanged(Change<? extends TypeOfTime> change) {
            typeOfTimeTable.setItems(TimeTracker.typeOfTimeObsList);
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    class DataOfTimeListener implements ListChangeListener<DataOfTime> {

        @Override
        public void onChanged(Change<? extends DataOfTime> change) {
            dataOfTimeTable.setItems(TimeTracker.dataOfTimeObsList);
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

}
