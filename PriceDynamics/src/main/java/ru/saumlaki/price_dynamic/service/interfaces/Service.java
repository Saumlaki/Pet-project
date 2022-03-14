package ru.saumlaki.price_dynamic.service.interfaces;

import java.util.List;

public interface Service<T> {

    /**
     * Метод добавления объекта в БД
     */
    void add(T object);

    /**
     * Метод удаления объекта из БП
     */
    void remove(T object);

    /**
     * Метод поиска объекта в БД по id
     */
    T getByID(int id);

    /**
     * Метод возвращает все объекты данного типа
     */
    List<T> getAll();
}