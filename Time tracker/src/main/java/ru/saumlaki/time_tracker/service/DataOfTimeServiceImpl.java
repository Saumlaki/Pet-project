package ru.saumlaki.time_tracker.service;

import ru.saumlaki.time_tracker.dao.DataOfTimeDAOImpl;
import ru.saumlaki.time_tracker.dao.interfaces.DataOfTimeDAO;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.interfaces.DataOfTimeService;

import java.util.List;

public class DataOfTimeServiceImpl implements DataOfTimeService {

    DataOfTimeDAO dao = new DataOfTimeDAOImpl();

    @Override
    public void add(DataOfTime object) {

        dao.add(object);
    }

    @Override
    public void update(DataOfTime object) {

        dao.update(object);
    }

    @Override
    public void remove(DataOfTime object) {

        dao.remove(object);
    }

    @Override
    public DataOfTime getByID(int id) {
        return dao.getByID(id);
    }

    @Override
    public List<DataOfTime> getAll() {
        return dao.getAll();
    }
}
