package ru.saumlaki.price_dynamic.view.list;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;
/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы списка цен
 */
@Component
public class PriceList extends AbstractView {

    public void showForm(Stage parentStage) {
        initialize("PriceView");

        setParentStage(parentStage);
        setTitle("Список: цены");
        setIcon("ListIcon");
        show();
    }
}
