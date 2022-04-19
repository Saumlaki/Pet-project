package ru.saumlaki.price_dynamic.starters.selectionGroup;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.selection.interfaces.Selectable;
import ru.saumlaki.price_dynamic.controllers.selectionGroup.ProductGroupSelectController;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractView;

/**
 * Класс инициализации формы выбора групп продуктов
 */
@Component
public class ProductGroupSelectStarter extends AbstractView<ProductGroupSelectController> {
    @Autowired
    ProductGroupSelectController controller;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage, Selectable resultAction) {
        title = "Список выбора групп товаров";
        iconProp = "GroupIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        controller.setResultAction(resultAction);
        show(parentStage);
    }
}
