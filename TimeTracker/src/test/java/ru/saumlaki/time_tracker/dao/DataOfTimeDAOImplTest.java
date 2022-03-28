package ru.saumlaki.time_tracker.dao;

import org.junit.jupiter.api.Test;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.dao.interfaces.DataOfTimeDAO;
import ru.saumlaki.time_tracker.dao.interfaces.TimeDAO;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.supporting.data.SimpleCalendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataOfTimeDAOImplTest {

    //Проверяемый dao
    DataOfTimeDAO dao = getDAO();

    //Элемент по которому идет проверка
    DataOfTime source = getTestElement();

    //Эталонный элемент
    DataOfTime expected = source;

    @Test
    void add() {
        //Инициализация теста
        init();

        //Добавление элемента
        dao.add(source);

        //Данные для проверки
        DataOfTime actual = dao.getByID(999);

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

        expected.setValues(expected.getValues() + 500);
        dao.update(expected);

        //Данные для проверки
        DataOfTime actual = dao.getByID(999);

        //Проверка данных
        assertEquals(expected.getValues(), actual.getValues());

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
        DataOfTime actual = dao.getByID(999);

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
        DataOfTime actual = dao.getByID(999);

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
        List<DataOfTime> actual = dao.getAll();

        //Проверка данных
        assertEquals(actual.stream().filter(expected::equals).count(), 1);

        //Удаление данных
        dao.remove(source);
    }

    private DataOfTime getTestElement() {

        return new DataOfTime(999,
                SimpleCalendar.getBeginningCurrentDay(),
                new Time(999, "Тестовые данные", new TypeOfTime(999, "Тестовые данные")),
                500);
    }

    private DataOfTimeDAO getDAO() {

        return new DataOfTimeDAOImpl();
    }

    private void init() {

        TimeTracker.createConnection();
        removeOldData();
    }

    private void removeOldData() {

        DataOfTime actual = dao.getByID(999);
        if (actual != null) {
            dao.remove(actual);
        }
    }
}