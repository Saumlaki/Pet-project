package ru.saumlaki.price_dynamic.service;

import ru.saumlaki.price_dynamic.dao.factory.DAOFactory;
import ru.saumlaki.price_dynamic.dao.interfaces.DAO;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;

import java.util.List;

public class ShopServiceImpl implements ShopService {

    DAO dao = DAOFactory.getDAO(Shop.class);

    @Override
    public void add(Shop object) {

    }

    @Override
    public void remove(Shop object) {

    }

    @Override
    public Shop getByID(int id) {
        return null;
    }

    @Override
    public List<Shop> getAll() {
        return null;
    }
}