package ru.saumlaki.price_dynamic.service.interfaces;

import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.factory.ServiceFactory;

import java.sql.SQLDataException;
import java.util.List;

/**
 * Интерфейс определяет бизнес логику работы с основными объектами программы
 */
public interface Service<T> {

    /**
     * Метод добавления объекта
     */
    void add(T object) throws SQLDataException;

    /**
     * Метод удаления объекта
     */
    void remove(T object);

    /**
     * Метод поиска объекта по id.
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
     * Метод обновления связанных списков
     */
    void updateList();
}
