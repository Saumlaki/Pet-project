package ru.saumlaki.time_tracker.service;

import ru.saumlaki.time_tracker.dao.DataOfTimeDAOImpl;
import ru.saumlaki.time_tracker.dao.interfaces.DataOfTimeDAO;
import ru.saumlaki.time_tracker.entity.DataOfTime;
import ru.saumlaki.time_tracker.entity.TypeOfTime;
import ru.saumlaki.time_tracker.service.interfaces.DataOfTimeService;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DataOfTimeServiceImpl implements DataOfTimeService {

    DataOfTimeDAO dao = new DataOfTimeDAOImpl();

    @Override
    public void add(DataOfTime object) {

        //Сохраняться только новые записи по времени.
        // Если на текущую дату есть запись с текущим днем и типом времени то просто приплюсовываем к ней значения
        List<DataOfTime> allElements = dao.getAll();

        if (allElements.contains(object)&&object.getId()==0) {

            DataOfTime oldDataOfTime = allElements.stream().filter(a -> a.equals(object)).findFirst().get();
            oldDataOfTime.setValues(oldDataOfTime.getValues() + object.getValues());
            dao.update(oldDataOfTime);
        } else if (object.getId() == 0) {

            object.setId(dao.getNextId());
            dao.add(object);
        } else

            dao.update(object);
    }

    @Override
    public void remove(DataOfTime object) {
        //Тут просто удаляем
        dao.remove(object);
    }

    @Override
    public DataOfTime getByID(int id) {
        return dao.getByID(id);
    }

    @Override
    public List<DataOfTime> getAll() {

        List<DataOfTime> list = dao.getAll();
        list.sort(Comparator.comparing(a -> a.getTime().toString()));
        return list;
    }

    public List<DataOfTime> getByPeriod(Date beginDate, Date endDate) {

        List<DataOfTime> list = dao.getAll();
        list = list.stream().filter(a-> a.getCalendar().getTime().getTime()>=beginDate.getTime() &&a.getCalendar().getTime().getTime()<=endDate.getTime()).
                sorted(Comparator.comparing(a -> a.getTime().toString())).collect(Collectors.toList());
        return list;
    }

}
