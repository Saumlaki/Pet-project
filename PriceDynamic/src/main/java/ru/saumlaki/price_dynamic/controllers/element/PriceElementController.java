package ru.saumlaki.price_dynamic.controllers.element;


import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Price;

import java.sql.SQLDataException;

@Component
@FxmlView("PriceElement.fxml")
public class PriceElementController extends AbstractElementController<Price> {

    @Override
    public void saveObject() {
        try {
            save("description");
        } catch (SQLDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateForm() {
        description.setText(object.getDescription());
    }

    @Override
    public void updateElement() {

    }
}
