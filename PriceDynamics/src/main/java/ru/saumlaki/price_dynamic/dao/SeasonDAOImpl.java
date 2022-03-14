package ru.saumlaki.price_dynamic.dao;

import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.dao.interfaces.SeasonDAO;
import ru.saumlaki.price_dynamic.entity.Season;

import java.util.List;

@Component
public class SeasonDAOImpl implements SeasonDAO {

    @Override
    public void add(Season object) {
    }

    @Override
    public void remove(Season object) {
    }

    @Override
    public Season getByID(int id) {
        return null;
    }

    @Override
    public List<Season> getAll() {
        return null;
    }
}