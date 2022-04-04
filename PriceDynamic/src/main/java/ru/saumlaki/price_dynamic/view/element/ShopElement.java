package ru.saumlaki.price_dynamic.view.element;

import javafx.stage.Stage;

import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;

/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы элемента
 */
public class ShopElement extends AbstractView {

    public void showForm(Stage parentStage, Shop object) {

        initialize("ShopElement");

        setParentStage(parentStage);
        setTitle("Элемент: " + object);
        setIcon("ListIcon");
        show();
    }
}