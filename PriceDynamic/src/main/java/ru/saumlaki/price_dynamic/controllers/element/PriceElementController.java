package ru.saumlaki.price_dynamic.controllers.element;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.view.selection.ProductSelectStarter;
import ru.saumlaki.price_dynamic.view.selection.ShopSelectStarter;

import java.time.LocalDate;

@Component
@FxmlView("PriceElement.fxml")
public class PriceElementController extends AbstractElementController<Price> {

    //***ПОЛЯ ФОРМЫ
    @FXML
    private TextField shop;

    @FXML
    private TextField product;

    @FXML
    private TextField value;

    @FXML
    private DatePicker data;

    //***БИНЫ
    @Autowired
    ProductSelectStarter productSelectStarter;

    @Autowired
    ShopSelectStarter shopSelectStarter;

    @Autowired
    PriceServiceImpl service;

    //***КНОПКИ ОТКРЫТИЯ ФОРМ ВЫБОРА
    @FXML
    void productSelect(ActionEvent event) {
        productSelectStarter.showForm(currentStage, selectObject -> {
            product.setText(selectObject.toString());
            protoObject.setValue("product", selectObject);
        });
    }

    @FXML
    void shopSelect(ActionEvent event) {
        shopSelectStarter.showForm(currentStage, selectObject ->
        {
            shop.setText(selectObject.toString());
            protoObject.setValue("shop", selectObject);
        });
    }


    //***МЕТОДЫ РАБОТЫ ОБЪЕКТ - ФОРМА
    @Override
    public void updateForm() {
        id.setText(String.valueOf(protoObject.getValue("id").toString()));
        shop.setText(protoObject.getValue("shop").toString());
        product.setText(protoObject.getValue("product").toString());
        value.setText(protoObject.getValue("price").toString());
        data.setValue((LocalDate) protoObject.getValue("date"));
    }

    @Override
    public void updateElement() {
        protoObject.setValue("description", description.getText());
        protoObject.setValue("price", value.getText());
        protoObject.setValue("date", data.getValue());
    }
}
