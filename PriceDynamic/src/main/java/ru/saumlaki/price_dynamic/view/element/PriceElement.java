package ru.saumlaki.price_dynamic.view.element;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;

/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы элемента
 */
public class PriceElement extends AbstractView {

    public void showForm(Stage parentStage, Price object) {

        initialize("PriceElement");

        setParentStage(parentStage);
        setTitle("Элемент: " + object);
        setIcon("ListIcon");
        show();
    }
}