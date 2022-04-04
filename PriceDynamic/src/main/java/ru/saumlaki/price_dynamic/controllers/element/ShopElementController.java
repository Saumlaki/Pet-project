package ru.saumlaki.price_dynamic.controllers.element;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Shop;

public class ShopElementController extends AbstractElementController<Shop> {

    @FXML
    private TextField description;

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
