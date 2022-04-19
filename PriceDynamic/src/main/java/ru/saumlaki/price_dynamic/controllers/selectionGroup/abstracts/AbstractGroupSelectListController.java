package ru.saumlaki.price_dynamic.controllers.selectionGroup.abstracts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import lombok.Setter;
import ru.saumlaki.price_dynamic.controllers.selection.interfaces.Selectable;
import ru.saumlaki.price_dynamic.controllers.listGroup.abstracts.AbstractTreeListController;
import ru.saumlaki.price_dynamic.supporting.Helper;

/**
 * Класс реализует базовую функциональность контролера выбора значения из иерархического списка значений
 */
public abstract class AbstractGroupSelectListController<T> extends AbstractTreeListController<T> {

    @Setter
    protected Selectable resultAction;

    @FXML
    private Button selectButton;

    @FXML
    private MenuItem selectButtonCM;

    @FXML
    public void initialize() {
        super.initialize();

        selectButton.setGraphic(new ImageView(Helper.getPropertyForName("SelectIcon")));
        selectButtonCM.setGraphic(new ImageView(Helper.getPropertyForName("SelectIcon")));
    }

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
        Object currentObject = getCurrentObject();
        if (currentObject != null) {
            selectObject();
        }
    }

    @Override
    protected void setOnMouseClicked() {
        list.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                selectObject();
            }
        });
    }

    public abstract void selectObject();
}
