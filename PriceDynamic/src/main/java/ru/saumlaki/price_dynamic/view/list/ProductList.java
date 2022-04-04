package ru.saumlaki.price_dynamic.view.list;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;
/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы списка продуктов
 */
@Component
public class ProductList extends AbstractView {

    public void showForm(Stage parentStage) {
        initialize("ProductView");

        setParentStage(parentStage);
        setTitle("Список: товары");
        setIcon("ListIcon");
        show();
    }
}
