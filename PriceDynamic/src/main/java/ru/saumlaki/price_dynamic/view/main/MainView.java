package ru.saumlaki.price_dynamic.view.main;


import ru.saumlaki.price_dynamic.controllers.main.MainController;

public class MainView extends AbstractView {

    public void showForm() {
        init("MainView");
        setTitle("Динамика цен");
        setIcon("Icon");
        show();

        ((MainController)getController()).init();
    }
}
