package ru.saumlaki.price_dynamic.controllers.element;


import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Price;

@Component
@FxmlView("PriceElement.fxml")
public class PriceElementController extends AbstractElementController<Price> {

    @Override
    public void saveObject() {
        save("description");
    }

    @Override
    public void updateForm() {
        description.setText(object.getDescription());
    }

    @Override
    public void updateElement() {

    }
}
