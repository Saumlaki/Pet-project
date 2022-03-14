package ru.saumlaki.price_dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.dao.interfaces.CityDAO;
import ru.saumlaki.price_dynamic.entity.City;
import ru.saumlaki.price_dynamic.service.interfaces.CityService;

import java.util.List;

@Component
public class CityServiceImpl implements CityService {

    @Autowired
    CityDAO dao;

    @Override
    public void add(City object) {

        dao.add(object);
    }

    @Override
    public void remove(City object) {

        dao.remove(object);
    }

    @Override
    public City getByID(int id) {

        return dao.getByID(id);
    }

    @Override
    public List<City> getAll() {

        return dao.getAll();
    }
}
