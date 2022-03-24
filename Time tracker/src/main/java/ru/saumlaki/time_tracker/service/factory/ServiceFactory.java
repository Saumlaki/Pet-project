package ru.saumlaki.time_tracker.service.factory;

import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.DataOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.TimeServiceImpl;
import ru.saumlaki.time_tracker.service.TypeOfTimeServiceImpl;
import ru.saumlaki.time_tracker.service.interfaces.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс реализует фабрику для получения сервисов по работе с БД
 */
public class ServiceFactory {

    /**
     * Метод возвращает объект <code>ServiceImpl</code> для заданного типа класса
     *
     * @return Service
     * @see TypeOfTime
     * @see Time
     * @see DataOfTime
     * @see Service
     */
    public static Service getService(Class tempClass) {

        Map<Class, Service> map = new HashMap<>();
        map.put(TypeOfTime.class, new TypeOfTimeServiceImpl());
        map.put(Time.class, new TimeServiceImpl());
        map.put(DataOfTime.class, new DataOfTimeServiceImpl());

        return map.get(tempClass);
    }
}