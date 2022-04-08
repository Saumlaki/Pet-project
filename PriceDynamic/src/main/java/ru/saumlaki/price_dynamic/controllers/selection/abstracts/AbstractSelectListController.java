package ru.saumlaki.price_dynamic.controllers.selection.abstracts;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.controllers.selection.interfaces.Selectable;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;
import ru.saumlaki.price_dynamic.supporting.AlertMessage;
import ru.saumlaki.price_dynamic.supporting.Helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс реализует базовую функциональность контролера для простого списка значений
 */
public abstract class AbstractSelectListController<T> extends AbstractListController<T> {

    Selectable resultAction;

    @FXML
    private Button selectButton;

    @FXML
    private MenuItem selectButtonCM;

    @FXML
    void selectOnAction(ActionEvent event) {
        Object curentObject = getCurrentObject();
        if (curentObject != null) {
            resultAction.onSelect(curentObject);
            closeForm();
        }
    }

    @FXML
    void selectOnActionCM(ActionEvent event) {
        Object curentObject = getCurrentObject();
        if (curentObject != null) {
            resultAction.onSelect(curentObject);
            closeForm();
        }
    }

    @Override
    protected void setOnMouseClicked() {
        list.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2){
                resultAction.onSelect(getCurrentObject());
            closeForm();}

        });
    }

    public void setResultAction(Selectable resultAction) {
        this.resultAction = resultAction;
    }
}
