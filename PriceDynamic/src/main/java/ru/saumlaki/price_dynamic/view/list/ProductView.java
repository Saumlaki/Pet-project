package ru.saumlaki.price_dynamic.view.list;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.controllers.list.PriceListController;
import ru.saumlaki.price_dynamic.controllers.list.ProductListController;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;

@Component
public class ProductView extends AbstractView {

    @Autowired
    @Qualifier("productListController")
    AbstractController abstractController;

    public void showForm(Stage parentStage) {
        initialize("ProductView");

        //TODO:SPRING
        abstractController = new ProductListController();
        abstractController.setCurrentStage(getCurrentStage());

        setParentStage(parentStage);
        setTitle("Список: товары");
        setIcon("ListIcon");
        show();
    }
}
