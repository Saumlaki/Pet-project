package ru.saumlaki.time_tracker.service;

import ru.saumlaki.time_tracker.dao.DataOfTimeDAOImpl;
import ru.saumlaki.time_tracker.dao.TypeOfTimeDAOImp;
import ru.saumlaki.time_tracker.dao.interfaces.DataOfTimeDAO;
import ru.saumlaki.time_tracker.dao.interfaces.TypeOfTimeDAO;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.interfaces.TypeOfTimeService;

import java.util.Comparator;
import java.util.List;

public class TypeOfTimeServiceImpl implements TypeOfTimeService {

    TypeOfTimeDAO dao = new TypeOfTimeDAOImp();

    @Override
    public void add(TypeOfTime object) {

        if (object.getId() == 0) {

            object.setId(dao.getNextId());
            dao.add(object);
        } else
            dao.update(object);
    }

    @Override
    public void remove(TypeOfTime object) {

        dao.remove(object);
    }

    @Override
    public TypeOfTime getByID(int id) {
        return dao.getByID(id);
    }

    @Override
    public List<TypeOfTime> getAll() {
        List<TypeOfTime> list = dao.getAll();
        list.sort(Comparator.comparing(a -> a.toString()));
        return dao.getAll();
    }
}
