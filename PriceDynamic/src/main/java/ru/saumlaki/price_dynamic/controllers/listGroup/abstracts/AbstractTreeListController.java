package ru.saumlaki.price_dynamic.controllers.listGroup.abstracts;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.controllers.listGroup.Imageeble;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;
import ru.saumlaki.price_dynamic.supporting.AlertMessage;
import ru.saumlaki.price_dynamic.supporting.Helper;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Класс реализует базовую функциональность иерархического списка значений
 */
public abstract class AbstractTreeListController<T> extends AbstractController {

    @Autowired
    private ObservableList<T> obsList;

    @FXML
    protected Button addButton;

    @FXML
    protected Button addGroupButton;

    @FXML
    protected Button changeButton;

    @FXML
    protected Button removeButton;

    @FXML
    protected MenuItem addButtonCM;

    @FXML
    protected MenuItem addGroupButtonCM;

    @FXML
    protected MenuItem changeButtonCM;

    @FXML
    protected MenuItem removeButtonCM;

    @Getter
    @FXML
    protected TreeTableView<T> list;

    //*****
    @FXML
    public void initialize() {
        addButton.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        addGroupButton.setGraphic(new ImageView(Helper.getPropertyForName("AddGroupIcon")));
        changeButton.setGraphic(new ImageView(Helper.getPropertyForName("ChangeElementIcon")));
        removeButton.setGraphic(new ImageView(Helper.getPropertyForName("RemoveElementIcon")));

        addButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        addGroupButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("AddGroupIcon")));
        changeButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("ChangeElementIcon")));
        removeButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("RemoveElementIcon")));

        createTableColumn();
        setOnMouseClicked();
        updateForm();
    }

    //*****

    @FXML
    void addOnAction(ActionEvent event) {
        addObject();
    }

    @FXML
    void addGroupOnAction(ActionEvent event) {
        addGroup();
    }

    @FXML
    void changeOnAction(ActionEvent event) {
        changeObject(getCurrentObject());
    }

    @FXML
    void removeOnAction(ActionEvent event) {
        removeObject(getCurrentObject());
    }

    //*****

    @FXML
    void addOnActionCM(ActionEvent event) {
        addObject();
    }

    @FXML
    void addGroupOnActionCM(ActionEvent event) {
        addGroup();
    }

    @FXML
    void changeOnActionCM(ActionEvent event) {
        changeObject(getCurrentObject());
    }

    @FXML
    void removeOnActionCM(ActionEvent event) {
        removeObject(getCurrentObject());
    }

    //*****

    protected T getCurrentObject() {

        if (list.getSelectionModel().getSelectedItem() == null) return null;
        else
            return list.getSelectionModel().getSelectedItem().getValue();
    }

    public abstract void addObject();

    public abstract void addGroup();

    public abstract void changeObject(T object);

    public abstract void removeObject(T object);

    public abstract void updateForm();

    //*****

    /**
     * Данный метод необходимо переопределить в классе наследнике и вызывать <code>createTableColumnForClass</code> с нужным типом класса
     */
    public abstract void createTableColumn();

    /**
     * Универсальный класс создания колонок.
     * Условия работы:
     * 1. Колонка должна быть аннотирована аннотацией <code>TableViewColumn</code>
     * 2. У колонки должен быть геттер, который возвращает либо <code>Integer</code> либо <code>String</code>
     *
     * @see TableViewColumn
     */
    public void createTableColumnForClass(Class objectClass) {

        /**Вспомогательный класс для создания корректного расположения колонок на форме. Сортирует колонки*/
        @AllArgsConstructor
        class IndexedColumn implements Comparable<IndexedColumn> {
            int order;
            String description;
            String name;

            @Override
            public int compareTo(IndexedColumn o) {
                return order - o.order;
            }
        }

        /**Получаем список колонок необходимых для добавления на форму.
         *Получаем с помощью рефлексии, опираясь на аннотацию <code>TableViewColumn</code>*/
        List<IndexedColumn> columnList = new ArrayList<>();
        Field fields[] = objectClass.getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(TableViewColumn.class)) {
                columnList.add(new IndexedColumn(field.getAnnotation(TableViewColumn.class).order(), field.getAnnotation(TableViewColumn.class).name(), field.getName()));
            }
        }

        Collections.sort(columnList);

        /**Добавляем колонки к таблице и устанавливаем для них источник данных*/
        for (IndexedColumn a : columnList) {

            /*Колонку с картинкой обрабатываем отдельно*/
            if (a.name.equals("image")) {

                /*Вводим вспомогательную колонку для корректного отображения маркера групп*/
                TreeTableColumn<T, String> columnTemp = new TreeTableColumn<>("");

                /*Колонка с картинкой*/
                TreeTableColumn<T, ImageView> column = new TreeTableColumn<>(a.description);
                column.setCellValueFactory(cellData-> new ObservableValueBase<ImageView>() {
                    @Override
                    public ImageView getValue() {
                        return ((Imageeble)cellData.getValue().getValue()).getImage();
                    }
                });

                columnTemp.setPrefWidth(50);
                list.getColumns().add(columnTemp);
                list.getColumns().add(column);
                continue;
            }

            TreeTableColumn<T, String> column = new TreeTableColumn<>(a.description);

            column.setCellValueFactory(StringCellDataFeatures -> {
                String value = null;
                try {
                    String methodName = "get" + a.name.substring(0, 1).toUpperCase() + a.name.substring(1);

                    Object currentValue = StringCellDataFeatures.getValue().getValue();
                    Object valueTemp = null;
                    if (currentValue != null)
                        valueTemp = currentValue.getClass().getDeclaredMethod(methodName).invoke(StringCellDataFeatures.getValue().getValue());

                    if (valueTemp == null) value = "-";
                    else if (valueTemp instanceof Double) value = String.valueOf(valueTemp);
                    else if (valueTemp instanceof Integer) value = String.valueOf(valueTemp);
                    else if (valueTemp instanceof String) value = (String) valueTemp;
                    else if (valueTemp instanceof Date) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
                        value = simpleDateFormat.format(valueTemp);
                    } else value = valueTemp.toString();

                } catch (Exception e) {
                    AlertMessage.show("Ошибка установки значения колонки", e.getMessage());
                }

                SimpleStringProperty simpleStringProperty = new SimpleStringProperty(value);
                return simpleStringProperty;
            });
            list.getColumns().add(column);
        }
    }

    //*****

    /**
     * Метод отвечает за обработку событий двойного щелчка мыши на таблице формы. По умолчанию открытие текущего элемента
     */
    protected void setOnMouseClicked() {
        list.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2)
                changeObject(getCurrentObject());
        });
    }

    protected void closeForm() {
        currentStage.close();
    }
}
