package ru.saumlaki.price_dynamic.service.interfaces;

import ru.saumlaki.price_dynamic.service.factory.ServiceFactory;

import java.util.List;

/**Интерфейс определяет бизнес логику работы с основными объектами программы*/
public interface Service<T> {

    /**
     * Метод добавления объекта
     */
    void add(T object);

    /**
     * Метод удаления объекта
     */
    void remove(T object);

    /**
     * Метод поиска объекта по id.
     * @return Null или найденный объект
     */
    T getByID(int id);

    /**
     * Метод возвращает все объекты данного типа
     * @return объект типа List
     */
    List<T> getAll();

    default Service<T> getDAO(Class objectClass){

        return ServiceFactory.getService(objectClass);
    }
}
