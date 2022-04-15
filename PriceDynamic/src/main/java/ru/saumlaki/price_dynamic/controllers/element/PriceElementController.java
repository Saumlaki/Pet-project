package ru.saumlaki.price_dynamic.controllers.element;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.MaskField;
import ru.saumlaki.price_dynamic.controllers.element.abstracts.AbstractElementController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.starters.selection.ProductSelectStarter;
import ru.saumlaki.price_dynamic.starters.selection.ShopSelectStarter;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.supporting.JFXTextField;

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
    private TextField price;

    @FXML
    private MaskField coefficient;

    @FXML
    private TextField unitPrice;

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

    @FXML
    public void initialize() {
        super.initialize();

        //Установка форматирования элементов ввода
        price.textProperty().addListener(new JFXTextField(price));
       // coefficient.textProperty().addListener(new JFXTextField(coefficient));
        unitPrice.textProperty().addListener(new JFXTextField(unitPrice));
    }

    //***ОБРАБОТЧИКИ ПОЛЕЙ ФОРМЫ

    @FXML
    void coefficientOnAction(ActionEvent event) {
        Double priceTemp = Double.valueOf(price.getText());
        Double coefficientTemp = Double.valueOf(coefficient.getText());
        unitPrice.setText(String.valueOf(priceTemp / coefficientTemp));
    }

    @FXML
    void priceOnAction(ActionEvent event) {
        Double priceTemp = Double.valueOf(price.getText());
        Double coefficientTemp = Double.valueOf(coefficient.getText());
        unitPrice.setText(String.valueOf(priceTemp / coefficientTemp));
    }


    //***МЕТОДЫ РАБОТЫ ОБЪЕКТ - ФОРМА
    @Override
    public void updateForm() {

        String priceStr = protoObject.getValue("price").toString();
        String unitPriceStr = protoObject.getValue("unitPrice").toString();
        String coefficientStr = protoObject.getValue("coefficient").toString();

        priceStr = priceStr.isEmpty()?"0":priceStr;
        unitPriceStr = unitPriceStr.isEmpty()?"0":unitPriceStr;
        coefficientStr = coefficientStr.isEmpty()?"0":coefficientStr;


        id.setText(String.valueOf(protoObject.getValue("id").toString()));
        shop.setText(protoObject.getValue("shop").toString());
        product.setText(protoObject.getValue("product").toString());
        price.setText(priceStr);
        coefficient.setNumber(coefficientStr);
        unitPrice.setText(unitPriceStr);
        data.setValue((LocalDate) protoObject.getValue("date"));
    }

    @Override
    public void updateElement() {
        protoObject.setValue("price", Double.valueOf(price.getText()));
        protoObject.setValue("date", data.getValue());
        protoObject.setValue("coefficient", Double.valueOf(coefficient.getText()));
        protoObject.setValue("unitPrice", Double.valueOf(unitPrice.getText()));
    }
}
