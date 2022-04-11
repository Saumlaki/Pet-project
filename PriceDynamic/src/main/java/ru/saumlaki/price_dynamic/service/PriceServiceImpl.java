package ru.saumlaki.price_dynamic.service;

import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.saumlaki.price_dynamic.dao.PriceDAOImpl;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.service.interfaces.PriceService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceDAOImpl dao;

    @Autowired
    ObservableList<Price> list;

    @Override
    public void add(Price object) {
        if (object.getId() == 0) dao.add(object);
        else dao.update(object);
    }

    @Override
    public void remove(Price object) {
        dao.remove(object);
        updateList();
    }

    @Override
    public Price getByID(int id) {
        return dao.getByID(id);
    }

    @Override
    public List<Price> getAll() {
        return dao.getAll();
    }

    @Override
    public void updateList() {
        list.clear();
        list.addAll(getAll());
    }
}