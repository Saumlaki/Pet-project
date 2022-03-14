package ru.saumlaki.price_dynamic.view.jfx.shop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.PriceDynamics;
import ru.saumlaki.price_dynamic.controller.interfaces.Refreshable;
import ru.saumlaki.price_dynamic.controller.jfx.shop.ShopListController;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;
import ru.saumlaki.price_dynamic.view.interfaces.Erroreble;
import ru.saumlaki.price_dynamic.view.interfaces.Showable;

import java.io.IOException;

@Component
public class ShopList implements Showable {

    @Autowired
    private ShopService shopService;

    @Override
    public void show(Stage stage, Refreshable refreshable) {

        if (stage == null) stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(PriceDynamics.class.getResource("/jfxView/shop/shopListView.fxml"));
        VBox root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            ((Erroreble) PriceDynamics.applicationContext.getBean("errorMessageView")).error("Ошибка загрузки формы списка магазинов",
                    e.getMessage());
        }
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());

        ShopListController shopListController= (ShopListController) fxmlLoader.getController();
        shopListController.addRefreshable(refreshable);
        shopListController.refresh();

        stage.setScene(scene);
        stage.show();
    }
}
//    TableView<Shop> tableView = (TableView<Shop>) scene.lookup("#table");