package ru.saumlaki.time_tracker.dao;

import org.junit.jupiter.api.Test;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.dao.interfaces.TimeDAO;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeDAOImplTest {

    //Проверяемый dao
    TimeDAO dao =  getDAO();

    //Элемент по которому идет проверка
    Time source = getTestElement();

    //Эталонный элемент
    Time expected = source;

    @Test
    void add() {
        //Инициализация теста
        init();

        //Добавление элемента
        dao.add(source);

        //Данные для проверки
        Time actual = dao.getByID(999);

        //Проверка данных
        assertEquals(expected, actual);

        //Удаление данных
        dao.remove(actual);
    }

    @Test
    void update() {

        //Инициализация теста
        init();

        //Добавление элемента
        dao.add(source);

        expected.setDescription(expected.getDescription() + "_new");
        dao.update(expected);

        //Данные для проверки
        Time actual = dao.getByID(999);

        //Проверка данных
        assertEquals(expected.getDescription(), actual.getDescription());

        //Удаление данных
        dao.remove(actual);
    }

    @Test
    void remove() {

        //Инициализация теста
        init();

        //Добавление элемента
        dao.add(source);

        //Получаем данные
        Time actual = dao.getByID(999);

        //Проверка данных
        assertEquals(expected, actual);

        //Удаление данных
        dao.remove(actual);

        //Проверяем что данные удалены
        actual = dao.getByID(999);
        assertEquals(actual, null);
    }

    @Test
    void getByID() {

        //Инициализация теста
        init();

        //Добавление элемента
        dao.add(source);

        //Получаем данные
        Time actual = dao.getByID(999);

        //Проверка данных
        assertEquals(expected.getId(), actual.getId());

        //Удаление данных
        dao.remove(actual);
    }

    @Test
    void getAll() {

        //Инициализация теста
        init();

        //Добавление элемента
        dao.add(source);

        //Данные для проверки
        List<Time> actual = dao.getAll();

        //Проверка данных
        assertEquals(actual.stream().filter(expected::equals).count(), 1);

        //Удаление данных
        dao.remove(source);

    }

    private Time getTestElement() {

        return new Time(999, "Тестовые данные", new TypeOfTime(999, "Тестовые данные"));
    }

    private TimeDAO getDAO() {

        return new TimeDAOImpl();
    }

    private void init() {

        TimeTracker.createConnection();
        removeOldData();
    }

    private void removeOldData() {

        Time actual = dao.getByID(999);
        if (actual != null) {
            dao.remove(actual);
        }
    }
}