package ru.saumlaki.price_dynamic.controller.interfaces;


/**
 * Интерфейс определяет метод начальной инициализации формы
 */
public interface Initializable {

    /**
     * В данном методе происходи инициализация настроек формы
     */
    void initialization();

    void initialization(Refreshable parentView);
}
