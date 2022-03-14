package ru.saumlaki.price_dynamic.view.jfx.shop;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controller.jfx.shop.ShopElementViewController;
import ru.saumlaki.price_dynamic.controller.jfx.shop.ShopListViewController;
import ru.saumlaki.price_dynamic.dao.interfaces.ShopDAO;
import ru.saumlaki.price_dynamic.entity.Shop;

import java.io.IOException;

@Component
public class ShopElementView {
    @Autowired
    private ShopDAO shopDAO;

    //Текущий магазин
    private Shop shop = null;

    public void show(Stage stage) {

        if (stage == null) stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/jfxView/shop/shopElementView.fxml"));
        VBox root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("-->Ошибка");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());

        ShopElementViewController controller = fxmlLoader.getController();

        stage.setScene(scene);
        stage.show();

        controller.setShop(shop);
    }

    /////////////////////////////////////////////////////////////////
    //Наполнение формы

    //Наполнение формы
    /////////////////////////////////////////////////////////////////

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
