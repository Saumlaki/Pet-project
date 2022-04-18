package ru.saumlaki.price_dynamic.starters.list;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.treeList.ProductListController;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractViewProto;

/**
 * Класс инициализации формы списка продуктов
 */
@Component
public class ProductListStarter extends AbstractViewProto<ProductListController> {
    @Autowired
    ProductListController controller;;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage) {
        title = "Список товаров";
        iconProp = "ListIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        show(parentStage);
    }
}
