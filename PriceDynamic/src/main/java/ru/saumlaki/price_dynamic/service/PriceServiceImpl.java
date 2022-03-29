package ru.saumlaki.price_dynamic.service;

import ru.saumlaki.price_dynamic.dao.factory.DAOFactory;
import ru.saumlaki.price_dynamic.dao.interfaces.DAO;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.service.interfaces.PriceService;

import java.util.List;

public class PriceServiceImpl implements PriceService {

    DAO dao = DAOFactory.getDAO(Price.class);

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