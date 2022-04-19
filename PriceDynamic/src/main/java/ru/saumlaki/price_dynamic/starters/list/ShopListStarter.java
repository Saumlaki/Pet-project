package ru.saumlaki.price_dynamic.starters.list;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.ShopListController;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractView;

/**
 * Класс инициализации формы списка магазинов
 */
@Component("shopListStarter")
public class ShopListStarter extends AbstractView<ShopListController> {
    @Autowired
    ShopListController controller;;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage) {
        title = "Список магазинов";
        iconProp = "ListIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        show(parentStage);
    }
}
