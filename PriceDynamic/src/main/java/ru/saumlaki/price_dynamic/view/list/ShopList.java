package ru.saumlaki.price_dynamic.view.list;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;
/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы списка магазинов
 */
@Component
public class ShopList extends AbstractView {

    public void showForm(Stage parentStage) {
        initialize("ShopView");

        setParentStage(parentStage);
        setTitle("Список: магазины");
        setIcon("ListIcon");
        show();
    }
}
