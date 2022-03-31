package ru.saumlaki.price_dynamic.view.list;

import ru.saumlaki.price_dynamic.view.main.AbstractView;

public class ShopView extends AbstractView {

    public void showForm() {
        init("ShopView");
        setTitle("Список: магазины");
        setIcon("ListIcon");
        show();
    }
}
