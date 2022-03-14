package ru.saumlaki.price_dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.saumlaki.price_dynamic.dao.interfaces.ShopDAO;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;

import java.util.List;

@Component
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopDAO dao;

    @Transactional
    @Override
    public void add(Shop object) {

        dao.add(object);
    }

    @Transactional
    @Override
    public void remove(Shop object) {

        dao.remove(object);
    }

    @Transactional
    @Override
    public Shop getByID(int id) {

        return dao.getByID(id);
    }

    @Transactional
    @Override
    public List<Shop> getAll() {

        return dao.getAll();
    }
}