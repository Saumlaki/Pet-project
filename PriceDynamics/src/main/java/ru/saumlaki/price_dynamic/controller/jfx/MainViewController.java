package ru.saumlaki.price_dynamic.controller.jfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableView;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.view.interfaces.Showable;
import ru.saumlaki.price_dynamic.view.jfx.shop.ShopListView;

public class MainViewController {


    @FXML
    private TreeTableView<Price> table;

    @FXML
    void cityOpenList(ActionEvent event) {
        Showable showable = (Showable) Main.applicationContext.getBean("cityListView");
        showable.show(null);
    }

    @FXML
    void shopOpenList(ActionEvent event) {

        Showable showable = (Showable) Main.applicationContext.getBean("shopListView");
        showable.show(null);
    }

    @FXML
    void seasonOpenList(ActionEvent event) {
        Showable showable = (Showable) Main.applicationContext.getBean("seasonListView");
        showable.show(null);
    }

    @FXML
    void productOpenList(ActionEvent event) {
        Showable showable = (Showable) Main.applicationContext.getBean("productListView");
        showable.show(null);
    }

    @FXML
    void priceOpenList(ActionEvent event) {
        Showable showable = (Showable) Main.applicationContext.getBean("priceListView");
        showable.show(null);
    }

    @FXML
    void about(ActionEvent event) {
        Showable showable = (Showable) Main.applicationContext.getBean("about");
        showable.show(null);
    }

    @FXML
    void close(ActionEvent event) {

    }
}