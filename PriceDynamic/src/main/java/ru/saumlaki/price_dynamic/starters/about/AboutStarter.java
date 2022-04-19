package ru.saumlaki.price_dynamic.starters.about;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.about.AboutController;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractView;

/**
 * Класс инициализации формы элемента
 */
@Component
public class AboutStarter extends AbstractView<AboutController> {
    @Autowired
    AboutController controller;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm(Stage parentStage) {
        title = "О программе...";
        iconProp = "AboutIcon";

        initialize(controller);
        show(parentStage);
    }
}