package ru.saumlaki.time_tracker.dao.interfaces;

import ru.saumlaki.time_tracker.DialogMessengerElementForm;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.TypeOfTime;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.Properties;

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
     * @return Null или найденный объект
     */
    T getByID(int id);

    /**
     * Метод возвращает все объекты данного типа
     * @return объект типа List
     */
    List<T> getAll();
}
