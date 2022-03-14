package ru.saumlaki.price_dynamic.view.jfx.shop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.PriceDynamics;
import ru.saumlaki.price_dynamic.controller.interfaces.Refreshable;
import ru.saumlaki.price_dynamic.controller.jfx.shop.ShopElementController;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.view.interfaces.Erroreble;
import ru.saumlaki.price_dynamic.view.interfaces.Showable;

import java.io.IOException;

@Component
public class ShopElement implements Showable {

    //Текущий магазин
    private Shop shop = null;

    @Override
    public void show(Stage stage, Refreshable refreshable) {

        if (stage == null) stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(PriceDynamics.class.getResource("/jfxView/shop/shopElementView.fxml"));
        VBox root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            ((Erroreble) PriceDynamics.applicationContext.getBean("errorMessageView")).error("Ошибка загрузки формы элемента магазина",
                    e.getMessage());
        }
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());

        ShopElementController controller = fxmlLoader.getController();

        stage.setScene(scene);
        stage.show();

        controller.setShop(shop);
        controller.setStage(stage);
        controller.addRefreshable(refreshable);
        controller.refresh();
    }

    /////////////////////////////////////////////////////////////////
    //Сеттеры

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
