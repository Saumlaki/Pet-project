package ru.saumlaki.price_dynamic.controller.jfx.shop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.view.jfx.shop.ShopElementView;

public class ShopListViewController {

    //Основная таблица формы "Продукты"
    @FXML
    private TableView<Shop> table;

    @FXML
    void add(ActionEvent event) {
        ShopElementView shopElementView = (ShopElementView) Main.applicationContext.getBean("shopElementView");
        shopElementView.show(null);
    }

    @FXML
    void change(ActionEvent event) {
        Shop shop = table.getSelectionModel().getSelectedItem();
        ShopElementView shopElementView = (ShopElementView) Main.applicationContext.getBean("shopElementView");
        shopElementView.setShop(shop);
        shopElementView.show(null);
    }

    @FXML
    void copy(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }


}

