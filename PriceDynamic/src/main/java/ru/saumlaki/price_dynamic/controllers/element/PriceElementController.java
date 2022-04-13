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
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.view.selection.ProductSelectStarter;
import ru.saumlaki.price_dynamic.view.selection.ShopSelectStarter;

import java.sql.SQLDataException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
@FxmlView("PriceElement.fxml")
public class PriceElementController extends AbstractElementController<Price> {

    @FXML
    private TextField id;

    @FXML
    private TextField shop;

    @FXML
    private TextField product;

    @FXML
    private TextField value;

    @FXML
    private DatePicker data;

    @Autowired
    ProductSelectStarter productSelectStarter;

    @Autowired
    ShopSelectStarter shopSelectStarter;

    @Autowired
    PriceServiceImpl service;


    @Override
    public void saveObject() {
        try {
            save("shop, product, date, price");
            closeForm();
        } catch (SQLDataException ex) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("Ошибка сохранения элемента");
            ex.printStackTrace();
        }
    }

    @Override
    public void updateForm() {
        id.setText(String.valueOf(object.getId()));
        shop.setText(getView(object.getShop()));
        product.setText(getView(object.getProduct()));
        value.setText(getView(object.getPrice()));

        data.setValue(object.getDate());
    }

    @Override
    public void updateElement() {
        object.setPrice(Integer.parseInt(value.getText()));
        object.setDate(data.getValue());
    }

    @FXML
    void productSelect(ActionEvent event) {
        productSelectStarter.showForm(currentStage, selectObject -> {
            product.setText(selectObject.toString());
            object.setProduct((Product) selectObject);
        });
    }

    @FXML
    void shopSelect(ActionEvent event) {
        shopSelectStarter.showForm(currentStage, selectObject ->
        {
            shop.setText(selectObject.toString());
            object.setShop((Shop) selectObject);
        });
    }

    public String getView(Object object) {
        if(object==null)return "";
        else return object.toString();

    }
}
