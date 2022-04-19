package ru.saumlaki.price_dynamic.starters.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.main.MainController;
import ru.saumlaki.price_dynamic.starters.abstracts.AbstractView;

/**
 * Класс инициализации главной формы
 */
@Component
public class MainViewStarter extends AbstractView<MainController> {
    @Autowired
    MainController controller;

    //***ИНДИВИДУАЛЬНЫЕ НАСТРОЙКИ ФОРМЫ
    public void showForm() {
        title = "Динамика цен";
        iconProp = "Icon";

        initialize(controller);
        controller.setCurrentStage(currentStage);
        show();
    }
}
