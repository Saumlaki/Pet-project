package ru.saumlaki.price_dynamic.starters.group;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.group.ProductGroupController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractView;

/**
 * Класс инициализации формы элемента
 */
@Component
public class ProductGroupStarter extends AbstractView<ProductGroupController> {
    @Autowired
    ProductGroupController controller;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage, Product object) {

        object.setGroup(true);

        title = "Группа товаров";
        iconProp = "GroupIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        controller.setObject(object);
        show(parentStage);
    }
}