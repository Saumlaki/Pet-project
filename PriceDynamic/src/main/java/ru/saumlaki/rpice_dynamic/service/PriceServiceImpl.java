package ru.saumlaki.rpice_dynamic.service;

import ru.saumlaki.rpice_dynamic.dao.factory.DAOFactory;
import ru.saumlaki.rpice_dynamic.dao.interfaces.DAO;
import ru.saumlaki.rpice_dynamic.entity.Shop;
import ru.saumlaki.rpice_dynamic.service.interfaces.ShopService;

import java.util.List;

public class PriceServiceImpl implements ShopService {

    DAO dao = DAOFactory.getDAO(Shop.class);

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
        return null;
    }

    @Override
    public List<Shop> getAll() {
        return null;
    }
}