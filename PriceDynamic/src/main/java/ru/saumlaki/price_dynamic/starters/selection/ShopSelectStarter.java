package ru.saumlaki.price_dynamic.starters.selection;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.selection.ShopSelectController;
import ru.saumlaki.price_dynamic.controllers.selection.interfaces.Selectable;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractView;

/**
 * Класс инициализации формы списка магазинов
 */
@Component
public class ShopSelectStarter extends AbstractView<ShopSelectController> {
    @Autowired
    ShopSelectController controller;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage, Selectable resultAction) {
        title = "Список выбора магазина";
        iconProp = "ListIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        controller.setResultAction(resultAction);
        show(parentStage);
    }
}
