package ru.saumlaki.price_dynamic.dao.interfaces;

import java.util.List;

/**
 * Интерфейс определяет основные методы работы с БД
 */
public interface DAO<T> {

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
