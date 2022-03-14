package ru.saumlaki.price_dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.dao.interfaces.PriceDAO;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.service.interfaces.PriceService;

import java.util.List;

@Component
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceDAO dao;

    @Override
    public void add(Price object) {

        dao.add(object);
    }

    @Override
    public void remove(Price object) {

        dao.remove(object);
    }

    @Override
    public Price getByID(int id) {

        return dao.getByID(id);
    }

    @Override
    public List<Price> getAll() {

        return dao.getAll();
    }
}
