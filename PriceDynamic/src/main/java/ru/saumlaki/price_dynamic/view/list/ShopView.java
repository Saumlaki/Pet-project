package ru.saumlaki.price_dynamic.view.list;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.controllers.list.PriceListController;
import ru.saumlaki.price_dynamic.controllers.list.ShopListController;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;

@Component
public class ShopView extends AbstractView {

    @Autowired
    @Qualifier("shopListController")
    AbstractController abstractController;

    public void showForm(Stage parentStage) {
        initialize("ShopView");

        //TODO:SPRING
        abstractController = new ShopListController();
        abstractController.setCurrentStage(getCurrentStage());

        setParentStage(parentStage);
        setTitle("Список: магазины");
        setIcon("ListIcon");
        show();
    }
}
