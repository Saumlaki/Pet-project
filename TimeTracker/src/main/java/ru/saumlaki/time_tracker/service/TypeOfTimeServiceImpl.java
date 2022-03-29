package ru.saumlaki.time_tracker.service;

import ru.saumlaki.time_tracker.dao.TypeOfTimeDAOImp;
import ru.saumlaki.time_tracker.dao.interfaces.TypeOfTimeDAO;
import ru.saumlaki.time_tracker.entity.Time;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;
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

        if(object!=null){
        //При удалении в начале удаляем все данные по типам времени, потом текущие данные
        //При удалении в начале удалим данные по временным затратам, потом переданные данные
        ServiceFactory.getService(Time.class).getAll().stream().filter(a->((Time)a).getTypeOfTime().equals(object)).forEach(a->ServiceFactory.getService(Time.class).remove(a));
        dao.remove(object);
        dao.remove(object);}
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
