package ru.saumlaki.price_dynamic.starters.element;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.element.ProductElementController;
import ru.saumlaki.price_dynamic.controllers.element.ProductGroupController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractViewProto;

/**
 * Класс инициализации формы элемента
 */
@Component
public class ProductGroupStarter extends AbstractViewProto<ProductGroupController> {
    @Autowired
    ProductGroupController controller;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage, Product object) {

        object.setGroup(true);

        title = "Группа товаров";
        iconProp = "StringIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        controller.setObject(object);
        show(parentStage);
    }
}