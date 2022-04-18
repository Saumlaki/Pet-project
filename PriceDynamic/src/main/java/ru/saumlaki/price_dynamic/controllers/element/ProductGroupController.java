package ru.saumlaki.price_dynamic.controllers.element;


import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;

@Component
@FxmlView("ProductGroup.fxml")
public class ProductGroupController extends AbstractElementController<Product> {
    @Autowired
    ProductServiceImpl service;

    //***МЕТОДЫ РАБОТЫ ОБЪЕКТ - ФОРМА
    @Override
    public void updateForm() {
        description.setText(protoObject.getValue("description").toString());
        id.setText(String.valueOf(protoObject.getValue("id").toString()));
    }

    @Override
    public void updateElement() {
        protoObject.setValue("description", description.getText());
    }
}
