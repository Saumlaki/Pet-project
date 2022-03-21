package ru.saumlaki.time_tracker.service;

import ru.saumlaki.time_tracker.dao.DataOfTimeDAOImpl;
import ru.saumlaki.time_tracker.dao.TimeDAOImpl;
import ru.saumlaki.time_tracker.dao.interfaces.DataOfTimeDAO;
import ru.saumlaki.time_tracker.dao.interfaces.TimeDAO;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.interfaces.TimeService;

import java.util.List;

public class TimeServiceImpl implements TimeService {

    TimeDAO dao = new TimeDAOImpl();

    @Override
    public void add(Time object) {

        dao.add(object);
    }

    @Override
    public void update(Time object) {

        dao.update(object);
    }

    @Override
    public void remove(Time object) {

        dao.remove(object);
    }

    @Override
    public Time getByID(int id) {
        return dao.getByID(id);
    }

    @Override
    public List<Time> getAll() {
        return dao.getAll();
    }
}
