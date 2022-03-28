package ru.saumlaki.time_tracker.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;
import ru.saumlaki.time_tracker.view.DataOfTimeElement;
import ru.saumlaki.time_tracker.view.TimeElement;
import ru.saumlaki.time_tracker.view.TypeOfTimeElement;

import java.awt.event.MouseEvent;

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

        //Создаем контекстные меню
        createContextMenu();

        //Подключаем слушателей к спискам
        TimeTracker.typeOfTimeObsList.addListener((ListChangeListener) change ->  typeOfTimeTable.setItems(TimeTracker.typeOfTimeObsList));
        TimeTracker.timeObsList.addListener((ListChangeListener) change -> timeTable.setItems(TimeTracker.timeObsList));
        TimeTracker.dataOfTimeObsListALL.addListener((ListChangeListener) change -> dataOfTimeTable.setItems(TimeTracker.dataOfTimeObsListALL));

        //Обновляем данные на форме
        typeOfTimeTable.setItems(TimeTracker.typeOfTimeObsList);
        timeTable.setItems(TimeTracker.timeObsList);
        dataOfTimeTable.setItems(TimeTracker.dataOfTimeObsListALL);
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

        ServiceFactory.getService(Time.class).remove(timeTable.getSelectionModel().getSelectedItem());

        TimeTracker.timeObsListUpdate();
        TimeTracker.dataOfTimeObsListUpdate();
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

        ServiceFactory.getService(TypeOfTime.class).remove(typeOfTimeTable.getSelectionModel().getSelectedItem());

        TimeTracker.typeOfTimeObsListUpdate();
        TimeTracker.timeObsListUpdate();
        TimeTracker.dataOfTimeObsListUpdate();
    }

    //---DataOfTime---

    @FXML
    void dataOfTimeAdd(ActionEvent event) {

        new DataOfTimeElement().showForm(null, new DataOfTime());
    }

    @FXML
    void dataOfTimeChange(ActionEvent event) {

        new DataOfTimeElement().showForm(null, dataOfTimeTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void dataOfTimeRemove(ActionEvent event) {

        ServiceFactory.getService(DataOfTime.class).remove(dataOfTimeTable.getSelectionModel().getSelectedItem());

        TimeTracker.dataOfTimeObsListUpdate();
    }

    //***Создание таблиц***

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



}