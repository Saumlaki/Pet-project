package ru.saumlaki.price_dynamic.starters.element;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.ShopElementController;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractView;

/**
 * Класс инициализации формы элемента
 */
@Component
public class ShopElementStarter extends AbstractView<ShopElementController> {
    @Autowired
    ShopElementController controller;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage, Shop object) {
        title = "Магазин";
        iconProp = "StringIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        controller.setObject(object);
        show(parentStage);
    }
}