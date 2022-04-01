package ru.saumlaki.price_dynamic.view.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.controllers.main.MainController;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;

@Component
public class MainView extends AbstractView {

    @Autowired
    @Qualifier("mainController")
    AbstractController controller;


    public void showForm() {

        // TODO: 01.04.2022 Перевести на SPRING ошибка совмещения версий JAVA
        controller = new MainController();

        initialize("MainView");

        controller.setCurrentStage(getCurrentStage());

        setTitle("Динамика цен");
        setIcon("Icon");
        show();
    }
}
