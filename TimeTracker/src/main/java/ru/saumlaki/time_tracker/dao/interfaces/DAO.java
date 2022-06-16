package ru.saumlaki.time_tracker.dao.interfaces;

import java.util.List;

/**
 * Интерфейс определяет основные методы для работы с БД
 */
public interface DAO<T> {

    /**
     * Метод добавления объекта в БД
     */
    void add(T object);

    /**
     * Метод обновления объекта в БД
     */
    void update(T object);

    /**
     * Метод удаления объекта из БД
     */
    void remove(T object);

    /**
     * Метод поиска объекта в БД по id.
     *
     * @return Null или найденный объект
     */
    T getByID(int id);

    /**
     * Метод возвращает все объекты данного типа
     *
     * @return объект типа List
     */
    List<T> getAll();

    /**
     * Метод возвращает значение следующего уникального ключа
     */
    int getNextId();

    /**
     * Метод создает таблицу в случае ее отсутствия
     */
    void createTable();
}
