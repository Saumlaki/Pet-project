package ru.saumlaki.price_dynamic.starters.list;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.list.PriceListController;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractViewProto;

/**
 * Класс инициализации формы списка цен
 */
@Component
public class PriceListStarter extends AbstractViewProto<PriceListController> {
    @Autowired
    PriceListController controller;;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage) {
        title = "Список цен";
        iconProp = "ListIcon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        show(parentStage);
    }
}
