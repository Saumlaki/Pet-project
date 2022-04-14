package ru.saumlaki.price_dynamic.controllers.element;

import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;

@Component
@FxmlView("ShopElement.fxml")
public class ShopElementController extends AbstractElementController<Shop> {
    @Autowired
    ShopServiceImpl service;

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
