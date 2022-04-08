package ru.saumlaki.price_dynamic.controllers.element;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;

import java.sql.SQLDataException;

@Component
@FxmlView("PriceElement.fxml")
public class PriceElementController extends AbstractElementController<Price> {
    @FXML
    private TextField id;

    @Autowired
    PriceServiceImpl service;

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
        id.setText(String.valueOf(String.valueOf(object.getId())));
    }

    @Override
    public void updateElement() {
    }
}
