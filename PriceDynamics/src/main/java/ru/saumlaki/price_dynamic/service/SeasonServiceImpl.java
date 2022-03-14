package ru.saumlaki.price_dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.dao.interfaces.SeasonDAO;
import ru.saumlaki.price_dynamic.entity.Season;
import ru.saumlaki.price_dynamic.service.interfaces.SeasonService;

import java.util.List;


@Component
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    SeasonDAO dao;

    @Override
    public void add(Season object) {

        dao.add(object);
    }

    @Override
    public void remove(Season object) {

        dao.remove(object);
    }

    @Override
    public Season getByID(int id) {

        return dao.getByID(id);
    }

    @Override
    public List<Season> getAll() {

        return dao.getAll();
    }
}
