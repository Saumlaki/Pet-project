package ru.saumlaki.price_dynamic.controllers.element;


import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Product;

@Component
@FxmlView("ProductElement.fxml")
public class ProductElementController extends AbstractElementController<Product> {

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
        object.setDescription(description.getText());
    }
}
