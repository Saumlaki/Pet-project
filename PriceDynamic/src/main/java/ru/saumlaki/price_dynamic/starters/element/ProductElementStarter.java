package ru.saumlaki.price_dynamic.starters.element;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controllers.element.ProductElementController;
import ru.saumlaki.price_dynamic.controllers.element.ShopElementController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractViewProto;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractView;

import java.io.IOException;
import java.net.URL;

/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы элемента
 */
@Component
public class ProductElementStarter extends AbstractViewProto<ProductElementController> {
    @Autowired
    ProductElementController controller;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage, Product object) {

        title = "Товар";
        iconProp = "StringIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        controller.setObject(object);
        show(parentStage);
    }
}