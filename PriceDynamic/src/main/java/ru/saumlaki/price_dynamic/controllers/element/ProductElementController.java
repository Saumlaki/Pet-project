package ru.saumlaki.price_dynamic.controllers.element;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;

import java.sql.SQLDataException;

@Component
@FxmlView("ProductElement.fxml")
public class ProductElementController extends AbstractElementController<Product> {

    @FXML
    private TextField id;

    @Autowired
    ProductServiceImpl service;

    @Override
    public void saveObject() {
        try {
            save("description");
            closeForm();
        } catch (SQLDataException ex) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("Ошибка сохранения элемента");
            ex.printStackTrace();
        }
    }

    @Override
    public void updateForm() {
        description.setText(object.getDescription());
    }

    @Override
    public void updateElement() {
        object.setDescription(description.getText());
    }
}
