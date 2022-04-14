package ru.saumlaki.price_dynamic.starters.element;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.PriceElementController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractViewProto;

/**
 * Класс инициализации формы элемента
 */
@Component
public class PriceElementStarter extends AbstractViewProto<PriceElementController> {
    @Autowired
    PriceElementController controller;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage, Price object) {
        title = "Цена";
        iconProp = "StringIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        controller.setObject(object);
        show(parentStage);
    }
}