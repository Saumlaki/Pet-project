package ru.saumlaki.price_dynamic.controllers.element;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.controllers.listGroup.abstracts.AbstractTreeListController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.starters.selection.ProductSelectStarter;
import ru.saumlaki.price_dynamic.starters.selectionGroup.ProductGroupSelectStarter;

import java.sql.SQLDataException;

@Component
@FxmlView("ProductElement.fxml")
public class ProductElementController extends AbstractElementController<Product> {
    @Autowired
    ProductServiceImpl service;

    @Autowired
    ProductGroupSelectStarter productGroupSelectStarter;

    @Autowired
    AbstractTreeListController<Product> productListController;

    @FXML
    private TextField group;

    //***МЕТОДЫ РАБОТЫ ОБЪЕКТ - ФОРМА
    @Override
    public void updateForm() {
        description.setText(protoObject.getValue("description").toString());
        id.setText(String.valueOf(protoObject.getValue("id").toString()));
        group.setText(String.valueOf(protoObject.getValue("parent").toString()));
    }

    @Override
    public void updateElement() {
        protoObject.setValue("description", description.getText());
    }

    @FXML
    void groupSelect(ActionEvent event) {
        productGroupSelectStarter.showForm(currentStage, selectObject -> {
            group.setText(selectObject.toString());
            protoObject.setValue("parent", selectObject);
        });
    }

    @Override
    public void saveOnAction(ActionEvent event) throws SQLDataException {
        super.saveOnAction(event);
        productListController.updateForm();
    }
}
