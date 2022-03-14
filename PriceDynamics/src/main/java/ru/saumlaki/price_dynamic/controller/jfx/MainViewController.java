package ru.saumlaki.price_dynamic.controller.jfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableView;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.entity.Price;

public class MainViewController {


    @FXML
    private TreeTableView<Price> table;

    @FXML
    void shopOpenList(ActionEvent event) {

    }

}