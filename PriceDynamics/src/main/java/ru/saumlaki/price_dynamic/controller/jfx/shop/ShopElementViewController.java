package ru.saumlaki.price_dynamic.controller.jfx.shop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import lombok.Setter;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.Service;
import ru.saumlaki.price_dynamic.view.interfaces.Erroreble;
import ru.saumlaki.price_dynamic.view.jfx.shop.ShopElementView;
import ru.saumlaki.price_dynamic.view.jfx.support.SaveNewElementView;

public class ShopElementViewController {

    private Shop shop;

    public void setShop(Shop shop) {
        this.shop = shop==null?new Shop():shop;
        this.name.setText(this.shop.getName());
    }

    @FXML
    private TextField name;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }

    @FXML
    void close(ActionEvent event) {
        //1. Спрашиваем надо ли сохранять если новый
        if (name.getText().isEmpty()) {

        }



//        if (shop.getId() == 0) {
//            SaveNewElementView saveNewElementView = (SaveNewElementView) Main.applicationContext.getBean("saveNewElementView");
//            saveNewElementView.show(null);
//        }else if(shop.getName().equals(name))
//
////        if(name.getText()!=shop.)
////        Stage stage = (Stage) name.getScene().getWindow();
////        stage.close();
    }

    @FXML
    void save(ActionEvent event) {

        if (name.getText().isEmpty()) {
            ((Erroreble) Main.applicationContext.getBean("errorMessageView")).error("Ошибка заполнения",
                    "Наименование магазина не может быть пустым");
        } else {
            Service<Shop> service = (Service<Shop>) Main.applicationContext.getBean("shopServiceImpl");
            shop.setName(name.getText());
            service.add(shop);
        }
    }
}

