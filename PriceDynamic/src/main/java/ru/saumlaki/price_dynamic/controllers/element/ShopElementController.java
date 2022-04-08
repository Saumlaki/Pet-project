package ru.saumlaki.price_dynamic.controllers.element;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;

import java.sql.SQLDataException;

@Component
@FxmlView("ShopElement.fxml")
public class ShopElementController extends AbstractElementController<Shop> {

    @FXML
    private TextField id;

    @Autowired
    ShopServiceImpl service;

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
        id.setText(String.valueOf(object.getId()));
    }

    @Override
    public void updateElement() {
        object.setDescription(description.getText());
    }
}
