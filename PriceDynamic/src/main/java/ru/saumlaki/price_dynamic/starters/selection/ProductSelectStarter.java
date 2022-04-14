package ru.saumlaki.price_dynamic.starters.selection;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.selection.ProductSelectController;
import ru.saumlaki.price_dynamic.controllers.selection.interfaces.Selectable;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractViewProto;

/**
 * Класс инициализации формы выбора продуктов
 */
@Component
public class ProductSelectStarter extends AbstractViewProto<ProductSelectController> {
    @Autowired
    ProductSelectController controller;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage, Selectable resultAction) {
        title = "Список выбора товара";
        iconProp = "ListIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        controller.setResultAction(resultAction);
        show(parentStage);
    }
}
