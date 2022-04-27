package ru.saumlaki.price_dynamic.controllers.selection.abstracts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.controllers.selection.interfaces.Selectable;

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
