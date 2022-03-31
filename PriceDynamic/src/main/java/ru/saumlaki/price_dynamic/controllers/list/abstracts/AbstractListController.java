package ru.saumlaki.price_dynamic.controllers.list.abstracts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import ru.saumlaki.price_dynamic.Helper;
import ru.saumlaki.price_dynamic.controllers.Init;
import ru.saumlaki.price_dynamic.entity.Shop;

/**
 * Класс реализует базовую функциональность контролера для простого списка значений
 */
public abstract class AbstractListController <T>{

    @FXML
    private Button addButton;

    @FXML
    private Button changeButton;

    @FXML
    private Button removeButton;

    @FXML
    private MenuItem addButtonCM;

    @FXML
    private MenuItem changeButtonCM;

    @FXML
    private MenuItem removeButtonCM;

    @FXML
    private TableView<T> list;

    //*****
    @FXML
    public void initialize() {

        addButton.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        changeButton.setGraphic(new ImageView(Helper.getPropertyForName("ChangeElementIcon")));
        removeButton.setGraphic(new ImageView(Helper.getPropertyForName("RemoveElementIcon")));

        addButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        changeButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("ChangeElementIcon")));
        removeButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("RemoveElementIcon")));
    }

    //*****

    @FXML
    void addOnAction(ActionEvent event) {

        addObject();
    }

    @FXML
    void changeOnAction(ActionEvent event) {

        changeObject(getCurrentObject());
    }

    @FXML
    void removeOnAction(ActionEvent event) {

        removeObject(getCurrentObject());
    }

    @FXML
    void addOnActionCM(ActionEvent event) {

        addObject();
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

    private T getCurrentObject() {

        return list.getSelectionModel().getSelectedItem();
    }

    public abstract void addObject();

    public abstract void changeObject(T object);

    public abstract void removeObject(T object);
}
