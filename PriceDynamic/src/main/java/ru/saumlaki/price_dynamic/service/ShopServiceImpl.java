package ru.saumlaki.price_dynamic.service;

import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.saumlaki.price_dynamic.dao.ShopDAOImpl;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDAOImpl dao;

    @Autowired
    ObservableList<Shop> list;

    @Override
    public void add(Shop object) {

        if (object.getId() == 0) dao.add(object);
        else dao.update(object);
    }

    @Override
    public void remove(Shop object) {
        dao.remove(object);
    }

    @Override
    public Shop getByID(int id) {
        return dao.getByID(id);
    }

    @Override
    public List<Shop> getAll() {
        return dao.getAll();
    }

    @Override
    public void updateList() {
        list.clear();
        list.addAll(getAll());
    }
}
