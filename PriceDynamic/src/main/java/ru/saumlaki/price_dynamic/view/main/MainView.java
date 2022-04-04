package ru.saumlaki.price_dynamic.view.main;

import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;
/**
 * 2022.04.04
 */

/**
 * Класс инициализации главной формы
 */
@Component
public class MainView extends AbstractView {

    public void showForm() {

        initialize("MainView");

        setTitle("Динамика цен");
        setIcon("Icon");
        show();
    }
}
