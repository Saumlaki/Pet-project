package ru.saumlaki.price_dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.dao.interfaces.ShopDAO;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;

import java.util.List;

@Component
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDAO dao;

    @Override
    public void add(Shop object) {

        dao.add(object);
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
}