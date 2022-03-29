package ru.saumlaki.time_tracker.service;

import ru.saumlaki.time_tracker.dao.TimeDAOImpl;
import ru.saumlaki.time_tracker.dao.interfaces.TimeDAO;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;
import ru.saumlaki.time_tracker.service.interfaces.TimeService;

import java.util.Comparator;
import java.util.List;

public class TimeServiceImpl implements TimeService {

    TimeDAO dao = new TimeDAOImpl();

    @Override
    public void add(Time object) {

        if (object.getId() == 0) {

            object.setId(dao.getNextId());
            dao.add(object);
        } else
            dao.update(object);
    }

    @Override
    public void remove(Time object) {

        //При удалении в начале удалим данные по временным затратам, потом переданные данные
        if(object!=null){
        ServiceFactory.getService(DataOfTime.class).getAll().stream().filter(a->((DataOfTime)a).getTime().equals(object)).forEach(a->ServiceFactory.getService(DataOfTime.class).remove(a));
        dao.remove(object);}
    }

    @Override
    public Time getByID(int id) {
        return dao.getByID(id);
    }

    @Override
    public List<Time> getAll() {
        List<Time> list = dao.getAll();
        list.sort(Comparator.comparing(a -> a.toString()));
        return list;
    }
}
