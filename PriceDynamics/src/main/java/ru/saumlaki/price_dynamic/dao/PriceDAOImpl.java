package ru.saumlaki.price_dynamic.dao;

import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.dao.interfaces.PriceDAO;
import ru.saumlaki.price_dynamic.entity.Price;

import java.util.List;

@Component
public class PriceDAOImpl implements PriceDAO {

    @Override
    public void add(Price object) {
    }

    @Override
    public void remove(Price object) {
    }

    @Override
    public Price getByID(int id) {
        return null;
    }

    @Override
    public List<Price> getAll() {
        return null;
    }
}